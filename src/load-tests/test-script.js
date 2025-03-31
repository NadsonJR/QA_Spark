import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate, Counter, Trend } from 'k6/metrics';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

// Custom metrics
const metrics = {
    errorRate: new Rate('errors'),
    successRate: new Rate('success_rate'),
    requestsPerSecond: new Rate('requests_per_second'),
    responseTimes: new Trend('response_times'),
    requests: new Counter('requests')
};

export let options = {
  stages: [
    { duration: '1m', target: 100 }, // ramp up to 100 users
    { duration: '3m', target: 500 }, // stay at 500 users for 3 minutes
    { duration: '1m', target: 0 },   // ramp down to 0 users
  ],
  thresholds: {
    'http_req_duration': ['p(95)<500'], // 95% of requests must complete below 500ms
    'errors': ['rate<0.01'], // error rate should be less than 1%
  },
};

// Initialize time tracking
const startTime = new Date().toISOString();
export default function () {
    const startReq = new Date();

    // Make the HTTP request
    let res = http.get('https://jsonplaceholder.typicode.com/posts');

    // Calculate response time
    const responseTime = new Date() - startReq;
    metrics.responseTimes.add(responseTime);
    metrics.requests.add(1);

    // Perform checks
    const checks = check(res, {
        'status is 200': (r) => r.status === 200,
        'response time is less than 500ms': (r) => r.timings.duration < 500,
    });
    if (!checks) {
        metrics.errorRate.add(1);
    } else {
        metrics.successRate.add(1);
    }
    metrics.requestsPerSecond.add(1);
    sleep(1);
}
export function handleSummary(data) {
    const endTime = new Date().toISOString();
    const summary = {
        testInfo: {
            startTime: startTime,
            endTime: endTime,
            duration: data.state.testRunDuration,
            scenarios: options.stages,
        },
        metrics: {
            totalRequests: data.metrics.iterations.values.count,
            errorRate: data.metrics.errors.values.rate,
            successRate: data.metrics.success_rate.values.rate,
            requestsPerSecond: data.metrics.requests_per_second.values.rate,
            responseTime: {
                min: data.metrics.http_req_duration.values.min,
                max: data.metrics.http_req_duration.values.max,
                avg: data.metrics.http_req_duration.values.avg,
                median: data.metrics.http_req_duration.values.med,
                p90: data.metrics.http_req_duration.values["p(90)"],
                p95: data.metrics.http_req_duration.values["p(95)"],
            },
        },
        thresholds: data.metrics.http_req_duration.thresholds,
    };
    return {
        'report/reportK6.html': htmlReport(data),
        'report/summary.json': JSON.stringify(summary, null, 2),
    };
}
