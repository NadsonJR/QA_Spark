import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate } from 'k6/metrics';

export let errorRate = new Rate('errors');

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

export default function () {
  let res = http.get('https://jsonplaceholder.typicode.com/posts');
  check(res, {
    'status is 200': (r) => r.status === 200,
    'response time is less than 500ms': (r) => r.timings.duration < 500,
  }) || errorRate.add(1);
  sleep(1);
}