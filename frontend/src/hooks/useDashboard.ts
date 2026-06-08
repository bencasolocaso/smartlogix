import { useQuery } from '@tanstack/react-query';
import { apiClient } from '../api/client';
import type { DashboardData } from '../types';

export const useDashboard = () => {
  return useQuery<DashboardData>({
    queryKey: ['dashboard'],
    queryFn: async () => {
      const { data } = await apiClient.get<DashboardData>('/bff/dashboard');
      return data;
    },
    retry: 1, // Let BFF handle the heavy retries
    refetchInterval: 10000, // Real-time feel
  });
};
