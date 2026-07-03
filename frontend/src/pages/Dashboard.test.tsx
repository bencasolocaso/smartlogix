import React from 'react';
import { render, screen } from '@testing-library/react';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import { Dashboard } from './Dashboard';
import { useDashboard } from '../hooks/useDashboard';

// Mock the useDashboard hook
vi.mock('../hooks/useDashboard', () => ({
  useDashboard: vi.fn(),
}));

describe('Dashboard Component', () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('renders loading spinner when isLoading is true', () => {
    vi.mocked(useDashboard).mockReturnValue({
      data: undefined,
      isLoading: true,
      error: null,
    } as any);

    const { container } = render(<Dashboard />);
    const spinner = container.querySelector('.animate-spin');
    expect(spinner).toBeInTheDocument();
  });

  it('renders connection error screen when error is present', () => {
    vi.mocked(useDashboard).mockReturnValue({
      data: undefined,
      isLoading: false,
      error: new Error('Failed to connect'),
    } as any);

    render(<Dashboard />);
    expect(screen.getByText('Connection Error')).toBeInTheDocument();
    expect(screen.getByText(/Failed to connect to the Gateway/i)).toBeInTheDocument();
  });

  it('renders dashboard data when loading is finished and data is present', () => {
    const mockData = {
      inventoryServiceDegraded: false,
      orderServiceDegraded: false,
      products: [
        { sku: 'SKU123', name: 'Product A', stockQuantity: 50 },
        { sku: 'SKU456', name: 'Product B', stockQuantity: 5 },
      ],
      recentOrders: [
        { id: 'ORDER12345678', customerId: 'CUST1', status: 'SHIPPED', items: [{}, {}] },
      ],
    };

    vi.mocked(useDashboard).mockReturnValue({
      data: mockData,
      isLoading: false,
      error: null,
    } as any);

    render(<Dashboard />);

    // Header checks
    expect(screen.getByText('SmartLogix Dashboard')).toBeInTheDocument();
    expect(screen.getByText('System Online')).toBeInTheDocument();

    // Inventory section checks
    expect(screen.getByText('Inventory Status')).toBeInTheDocument();
    expect(screen.getByText('Product A')).toBeInTheDocument();
    expect(screen.getByText('SKU123')).toBeInTheDocument();
    expect(screen.getByText('50')).toBeInTheDocument();

    // Low stock class check (red text check)
    const lowStockCount = screen.getByText('5');
    expect(lowStockCount).toHaveClass('text-red-500');

    // Orders section checks
    expect(screen.getByText('Recent Orders')).toBeInTheDocument();
    expect(screen.getByText('Customer: CUST1')).toBeInTheDocument();
    expect(screen.getByText('Items: 2')).toBeInTheDocument();
  });

  it('renders fallback messages when data is empty', () => {
    vi.mocked(useDashboard).mockReturnValue({
      data: {
        products: [],
        recentOrders: [],
        inventoryServiceDegraded: false,
        orderServiceDegraded: false,
      },
      isLoading: false,
      error: null,
    } as any);

    render(<Dashboard />);

    expect(screen.getByText('No inventory data available')).toBeInTheDocument();
    expect(screen.getByText('No recent orders found')).toBeInTheDocument();
  });
});
