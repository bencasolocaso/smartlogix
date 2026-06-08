export interface Product {
  sku: string;
  name: string;
  stockQuantity: number;
}

export interface OrderItem {
  sku: string;
  quantity: number;
}

export interface Order {
  id: string;
  customerId: string;
  status: string;
  items: OrderItem[];
}

export interface DashboardData {
  products: Product[];
  inventoryServiceDegraded: boolean;
  recentOrders: Order[];
  orderServiceDegraded: boolean;
}
