insert into room_category (id, identifier, usable, occupiable) values (1, 'OFFICE', true, true);
insert into room_category (id, identifier, usable, occupiable) values (2, 'MEETING', true, true);
insert into room_category (id, identifier, usable, occupiable) values (3, 'SUPPORT', true, false);

insert into room_type (id, identifier, room_category_id) values (1, 'OFFICE', 1);
insert into room_type (id, identifier, room_category_id) values (2, 'MEETING', 2);
insert into room_type (id, identifier, room_category_id) values (3, 'CONF', 2);
insert into room_type (id, identifier, room_category_id) values (4, 'CAFETERIA', 3);

insert into building_type (id, identifier) values (1, 'OFFICE');

insert into building_type_lang (id, locale, name, description) values (1, 'nl', 'Kantoor', 'Kantoorruimte');
insert into building_type_lang (id, locale, name, description) values (1, 'fr', 'Bureau', 'Espace bureau');


insert into building(id, building_type_id, identifier, ownership, status, created_date) values (1, 1, 'HQ', 'OWNED', 'ACTIVE', CURRENT_TIMESTAMP());
insert into floor(id, building_id, identifier, name, created_date) values (1, 1, '00', '00', CURRENT_TIMESTAMP());

insert into room(id, floor_id, identifier, name, room_type_id, created_date) values (1, 1, '100', '100', 1, CURRENT_TIMESTAMP());
insert into room(id, floor_id, identifier, name, room_type_id, created_date) values (2, 1, '101', '101', 2, CURRENT_TIMESTAMP());
insert into room(id, floor_id, identifier, name, room_type_id, created_date) values (3, 1, '200', '200', 3, CURRENT_TIMESTAMP());