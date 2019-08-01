DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS players;

CREATE TABLE teams (
    id uuid default random_uuid() primary key,
    name varchar(250)
);

CREATE TABLE players (
    id varchar(250) primary key,
    first_name varchar(250),
    last_name varchar(250),
    team_id varchar(250)
);

INSERT INTO teams (id, name) VALUES
  ('d8343306-19b2-4b46-9dd9-69cad0b1338a', 'Blue Jays'),
  ('d8a8d498-7387-44f9-bf57-9ed91e317f3e', 'Red Sox'),
  ('55915a91-f496-466b-add9-e7965f172fee', 'Dodgers');

INSERT INTO players (id, first_name, last_name, team_id)
VALUES
       ('55915a91-f496-466b-add9-e7965f172fee', 'Vladimir', 'Guerrero', 'd8343306-19b2-4b46-9dd9-69cad0b1338a'),
       ('d8343306-19b2-4b46-9dd9-69cad0b1338a', 'Marcus', 'Stroman', 'd8343306-19b2-4b46-9dd9-69cad0b1338a'),
       ('d8343306-19b2-4b46-9dd9-69cad0b1238a', 'Mookie', 'Betts', 'd8a8d498-7387-44f9-bf57-9ed91e317f3e'),
       ('d8a8d498-7387-44f9-bf57-9ed91e315f3e', 'Chris', 'Sale', 'd8a8d498-7387-44f9-bf57-9ed91e317f3e'),
       ('d8a8d498-7387-44f9-bf57-9ed91b317f3e', 'Kike', 'Hernandez', '55915a91-f496-466b-add9-e7965f172fee'),
       ('55915a91-f496-466b-add9-e7965f172fte', 'Joc', 'Pederson', '55915a91-f496-466b-add9-e7965f172fee')
       ;



