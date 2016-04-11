package com.company;

import com.google.inject.ImplementedBy;

@ImplementedBy(OrderTransformer.class)
interface IOrderTransformer {

	abstract String toString(Order order);

	abstract Order fromString(String s);

}