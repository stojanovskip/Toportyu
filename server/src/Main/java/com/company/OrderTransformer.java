package com.company;

class OrderTransformer implements IOrderTransformer {
    @Override
	public Order fromString(String s) {
        Order order = new Order();
        order.setContent(s);
        return order;
    }

	@Override
	public String toString(Order order) {
		return order.getContent();
	}
}
