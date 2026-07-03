import { describe, it, expect } from 'vitest';
import { apiClient } from './client';

describe('apiClient', () => {
  it('should have the correct baseURL', () => {
    expect(apiClient.defaults.baseURL).toBe('http://localhost:9080/api');
  });

  it('should have Content-Type header set to application/json', () => {
    expect(apiClient.defaults.headers['Content-Type']).toBe('application/json');
  });
});
