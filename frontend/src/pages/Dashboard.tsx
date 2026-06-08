import React from 'react';
import { Package, ShoppingCart, AlertTriangle, Activity } from 'lucide-react';
import { useDashboard } from '../hooks/useDashboard';

export const Dashboard: React.FC = () => {
  const { data, isLoading, error } = useDashboard();

  if (isLoading) {
    return (
      <div className="flex h-screen items-center justify-center bg-gray-50">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-brand-500"></div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex h-screen items-center justify-center bg-gray-50 p-4">
        <div className="glass-panel p-8 max-w-md w-full text-center">
          <AlertTriangle className="mx-auto h-12 w-12 text-red-500 mb-4" />
          <h2 className="text-xl font-bold text-gray-900 mb-2">Connection Error</h2>
          <p className="text-gray-600">Failed to connect to the Gateway. Please ensure all backend services are running.</p>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 p-6 md:p-12">
      <header className="mb-10 flex justify-between items-center">
        <div>
          <h1 className="text-3xl font-bold text-gray-900 tracking-tight">SmartLogix Dashboard</h1>
          <p className="text-gray-500 mt-1">Real-time logistics overview</p>
        </div>
        <div className="flex items-center gap-2 px-4 py-2 bg-green-100 text-green-700 rounded-full text-sm font-medium">
          <Activity size={16} />
          System Online
        </div>
      </header>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
        {/* Inventory Section */}
        <section className="glass-panel p-6 flex flex-col">
          <div className="flex items-center justify-between mb-6">
            <div className="flex items-center gap-3">
              <div className="p-3 bg-brand-100 text-brand-600 rounded-xl">
                <Package size={24} />
              </div>
              <h2 className="text-xl font-bold text-gray-900">Inventory Status</h2>
            </div>
            {data?.inventoryServiceDegraded && (
              <span className="flex items-center gap-1 text-xs font-medium text-amber-600 bg-amber-50 px-2 py-1 rounded-md">
                <AlertTriangle size={12} /> Degraded Mode
              </span>
            )}
          </div>
          
          <div className="flex-1 overflow-auto">
            {data?.products && data.products.length > 0 ? (
              <div className="space-y-4">
                {data.products.map(product => (
                  <div key={product.sku} className="flex justify-between items-center p-4 bg-white/50 rounded-xl border border-gray-100 hover:bg-white transition-colors">
                    <div>
                      <p className="font-semibold text-gray-900">{product.name}</p>
                      <p className="text-sm text-gray-500">{product.sku}</p>
                    </div>
                    <div className="text-right">
                      <p className={`font-bold text-lg ${product.stockQuantity < 10 ? 'text-red-500' : 'text-gray-900'}`}>
                        {product.stockQuantity}
                      </p>
                      <p className="text-xs text-gray-500 uppercase">In Stock</p>
                    </div>
                  </div>
                ))}
              </div>
            ) : (
              <div className="h-full flex flex-col items-center justify-center text-gray-400 py-12">
                <Package size={48} className="mb-4 opacity-20" />
                <p>No inventory data available</p>
              </div>
            )}
          </div>
        </section>

        {/* Orders Section */}
        <section className="glass-panel p-6 flex flex-col">
          <div className="flex items-center justify-between mb-6">
            <div className="flex items-center gap-3">
              <div className="p-3 bg-blue-100 text-blue-600 rounded-xl">
                <ShoppingCart size={24} />
              </div>
              <h2 className="text-xl font-bold text-gray-900">Recent Orders</h2>
            </div>
            {data?.orderServiceDegraded && (
              <span className="flex items-center gap-1 text-xs font-medium text-amber-600 bg-amber-50 px-2 py-1 rounded-md">
                <AlertTriangle size={12} /> Degraded Mode
              </span>
            )}
          </div>

          <div className="flex-1 overflow-auto">
            {data?.recentOrders && data.recentOrders.length > 0 ? (
              <div className="space-y-4">
                {data.recentOrders.map(order => (
                  <div key={order.id} className="p-4 bg-white/50 rounded-xl border border-gray-100 hover:bg-white transition-colors">
                    <div className="flex justify-between items-start mb-2">
                      <div>
                        <p className="text-xs text-gray-500 uppercase tracking-wider mb-1">Order ID</p>
                        <p className="font-mono text-sm text-gray-900">{order.id.substring(0,8)}...</p>
                      </div>
                      <span className="px-2.5 py-1 bg-green-100 text-green-700 text-xs font-bold rounded-full">
                        {order.status}
                      </span>
                    </div>
                    <div className="pt-2 border-t border-gray-100 mt-2">
                      <p className="text-sm text-gray-600"><span className="font-medium text-gray-900">Customer:</span> {order.customerId}</p>
                      <p className="text-sm text-gray-600"><span className="font-medium text-gray-900">Items:</span> {order.items?.length || 0}</p>
                    </div>
                  </div>
                ))}
              </div>
            ) : (
              <div className="h-full flex flex-col items-center justify-center text-gray-400 py-12">
                <ShoppingCart size={48} className="mb-4 opacity-20" />
                <p>No recent orders found</p>
              </div>
            )}
          </div>
        </section>
      </div>
    </div>
  );
};
