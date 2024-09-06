create table customers (
	id serial primary key,
	name varchar(255)
);

create table orders (
	id bigint not null primary key,
	total numeric(38,2),
	customer_id bigint,
	foreign key (customer_id) references customers (id)
);

create table order_items (
	id serial primary key,
	product varchar(255),
	quantity integer,
	price numeric(38,2),
	order_id bigint,
	foreign key (order_id) references orders (id)
);
