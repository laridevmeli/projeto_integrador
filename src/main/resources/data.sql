insert into country (name, initials, created_at, updated_at) values ("Brazil", "BRA", now(), now());
insert into country (name, initials, created_at, updated_at) values ("Argentine", "ARG", now(), now());

insert into state (name, timezone, country_id, initials, created_at, updated_at) values ("São Paulo", "America/Sao_Paulo", 1, "SP", now(), now());
insert into state (name, timezone, country_id, initials, created_at, updated_at) values ("Santa Catarina", "America/Sao_Paulo", 1, "SC", now(), now());
insert into state (name, timezone, country_id, initials, created_at, updated_at) values ("Sergipe", "America/Sao_Paulo", 1, "SE", now(), now());

insert into city (name, state_id, created_at, updated_at) values ("São Paulo", 1, now(), now());
insert into city (name, state_id, created_at, updated_at) values ("Florianópolis", 2, now(), now());
insert into city (name, state_id, created_at, updated_at) values ("Aracaju", 3, now(), now());

insert into zipcode (latitude, longitude, zip_code, city_id, created_at, updated_at) values (-23.5681, -46.6492, "01311100", 3, now(), now());
insert into zipcode (latitude, longitude, zip_code, city_id, created_at, updated_at) values (-27.5969, -48.5495, "88010300", 3, now(), now());
insert into zipcode (latitude, longitude, zip_code, city_id, created_at, updated_at) values (-10.9403, -37.0876, "49095650", 3, now(), now());

insert into address (street, number, zipcode_id) values ("Av Paulista", 1, 1);
insert into address (street, number, zipcode_id) values ("Rua Tenente Silva", 60, 2);
insert into address (street, number, zipcode_id) values ("Av Escritor Graciliano Ramos", 12, 3);

-- inserções baseadas no armazém
insert into warehouse (code, address_id) values ("MLB-SP", 1);
insert into warehouse (code, address_id) values ("MLB-SC", 2);

insert into representant (name, warehouse_id) values("Xandão", 2);
insert into representant (name, warehouse_id) values("Evy", 1);
insert into representant (name, warehouse_id) values("Becca", 1);

insert into seller (name) values("Lari");
insert into seller (name) values("Isinha");
insert into seller (name) values("Lulu");

insert into customer(name) values ("João da Silva");

-- section

INSERT INTO section VALUES(null, 200, 0, 'FS', 1);
INSERT INTO section VALUES(null, 200, 1, 'RF', 1);
INSERT INTO section VALUES(null, 200, 2, 'FF', 1);

INSERT INTO section VALUES(null, 200, 0, 'FS', 2);
INSERT INTO section VALUES(null, 200, 1, 'RF', 2);
INSERT INTO section VALUES(null, 200, 2, 'FF', 2);

-- advertisement

INSERT INTO advertisement values(null, "description", 200.0, "leite", 1);
