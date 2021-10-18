CREATE DATABASE IF NOT EXISTS Songs;
USE Songs;
DROP TABLE IF EXISTS Songs;
	CREATE TABLE Songs (
		title CHAR(50), 
		artist CHAR(50), 
		mood CHAR(50));
	INSERT INTO Songs VALUES ('Walking on Sunshine','Katrina and the Waves','happy');
	INSERT INTO Songs VALUES ('Poison and Wine','The Civil Wars','sad');
	INSERT INTO Songs VALUES ('So What','Pink','angry');
	INSERT INTO Songs VALUES ('Hurt','Johnny Cash','sad');
	INSERT INTO Songs VALUES ('Happy to be With You','Johnny Cash','happy');