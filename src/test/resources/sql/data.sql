INSERT INTO public.order_status (code, title) VALUES ('UNKNOWN', 'Неизвестный');
INSERT INTO public.order_status (code, title) VALUES ('NEW', 'Новый');
INSERT INTO public.order_status (code, title) VALUES ('IN_PROGRES', 'Доставка');
INSERT INTO public.order_status (code, title) VALUES ('CANCELED', 'Отменён');
INSERT INTO public.order_status (code, title) VALUES ('DELIVERED', 'Доставлен');

INSERT INTO public.couriers (name, phone) VALUES ('Вася Быстрый', '79990000001');
INSERT INTO public.couriers (name, phone) VALUES ('Петя Меткий', '79990000002');
INSERT INTO public.couriers (name, phone) VALUES ('Наташа Чёткая', '79990000003');

INSERT INTO public.customers (name, phone, email) VALUES ('ООО "Рога и копыта"', '+73832303030', 'besthorns@mail.ru');
INSERT INTO public.customers (name, phone, email) VALUES ('Сидоров Иван Петрович', '+79133234589', 'sidorov@mail.ru');

INSERT INTO public.addresses (city_name, street_name, house, building, flat) VALUES ('Новосибирск', 'Большевистская улица', ' 101', null, '1002');
INSERT INTO public.addresses (city_name, street_name, house, building, flat) VALUES ('Москва', 'Манежная улица', ' 2-10соор2', null, null);