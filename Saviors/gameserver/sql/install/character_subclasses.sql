CREATE TABLE IF NOT EXISTS `character_subclasses` (
	`char_obj_id` INT NOT NULL,
	`class_id` TINYINT UNSIGNED NOT NULL,
	`level` TINYINT UNSIGNED NOT NULL DEFAULT '1',
	`exp` bigint UNSIGNED NOT NULL DEFAULT '0',
	`sp` bigint UNSIGNED NOT NULL DEFAULT '0',
	`curHp` DECIMAL(11,4)	UNSIGNED NOT NULL DEFAULT '0',
	`curMp` DECIMAL(11,4)	UNSIGNED NOT NULL DEFAULT '0',
	`curCp` DECIMAL(11,4) UNSIGNED NOT NULL DEFAULT '0',
	`maxHp` MEDIUMINT	UNSIGNED NOT NULL DEFAULT '0',
	`maxMp` MEDIUMINT	UNSIGNED NOT NULL DEFAULT '0',
	`maxCp` MEDIUMINT	UNSIGNED NOT NULL DEFAULT '0',
	`active` BOOLEAN NOT NULL DEFAULT '0',
	`type` TINYINT UNSIGNED NOT NULL DEFAULT '0',
	PRIMARY KEY  (`char_obj_id`,`class_id`)
) ENGINE=MyISAM;