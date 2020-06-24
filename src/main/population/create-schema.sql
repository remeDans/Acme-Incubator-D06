
    create table `accounting_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `status` varchar(255),
        `title` varchar(255),
        `bookkeeper_id` integer not null,
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity` (
       `id` integer not null,
        `version` integer not null,
        `budget_amount` double precision,
        `budget_currency` varchar(255),
        `end_moment` datetime(6),
        `start_moment` datetime(6),
        `title` varchar(255),
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `investment_money_offer_amount` double precision,
        `investment_money_offer_currency` varchar(255),
        `justification` varchar(255),
        `statement` varchar(255),
        `status` varchar(255),
        `ticker` varchar(255),
        `investment_round_id` integer not null,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `target_url` varchar(255),
        `patron_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm_name` varchar(255),
        `responsibility_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper_request` (
       `id` integer not null,
        `version` integer not null,
        `firm_name` varchar(255),
        `responsibility_statement` varchar(255),
        `status` varchar(255),
        `user_account_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `goal_average_level` varchar(255),
        `goal_expert_level` varchar(255),
        `goal_rookie_level` varchar(255),
        `reward_average_level_amount` double precision,
        `reward_average_level_currency` varchar(255),
        `reward_expert_level_amount` double precision,
        `reward_expert_level_currency` varchar(255),
        `reward_rookie_level_amount` double precision,
        `reward_rookie_level_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `credit_card` (
       `id` integer not null,
        `version` integer not null,
        `cvv` integer not null,
        `brand` varchar(255),
        `holder_name` varchar(255),
        `month_expired` integer not null,
        `number` varchar(255),
        `year_expired` integer not null,
        `banner_id` integer,
        `patron_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `customisation` (
       `id` integer not null,
        `version` integer not null,
        `activity_sectors` varchar(255),
        `spam_threshold` double precision,
        `spam_words_en` varchar(255),
        `spam_words_es` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `dans_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `entrepreneur` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `qualification_record` varchar(255),
        `skills_record` varchar(255),
        `startup_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `forum` (
       `id` integer not null,
        `version` integer not null,
        `title` varchar(255),
        `creator_id` integer not null,
        `investment_round_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquiry` (
       `id` integer not null,
        `version` integer not null,
        `contact_email` varchar(255),
        `creation_moment` datetime(6),
        `deadline` datetime(6),
        `max_interval_money_amount` double precision,
        `max_interval_money_currency` varchar(255),
        `min_interval_money_amount` double precision,
        `min_interval_money_currency` varchar(255),
        `paragraphs_description` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round` (
       `id` integer not null,
        `version` integer not null,
        `amount_of_money_amount` double precision,
        `amount_of_money_currency` varchar(255),
        `creation_moment` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `kind_of_round` varchar(255),
        `link` varchar(255),
        `status` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        `entrepreneur_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `firm_name` varchar(255),
        `profile_biography` varchar(255),
        `profile_photo` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `optional_list_tags` varchar(255),
        `title` varchar(255),
        `creator_id` integer not null,
        `forum_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `deadline` datetime(6),
        `header_picture` varchar(255),
        `optional_links` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `contact_email` varchar(255),
        `creation_moment` datetime(6),
        `deadline` datetime(6),
        `max_interval_money_amount` double precision,
        `max_interval_money_currency` varchar(255),
        `min_interval_money_amount` double precision,
        `min_interval_money_currency` varchar(255),
        `paragraphs_description` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `participant` (
       `id` integer not null,
        `version` integer not null,
        `authenticated_id` integer not null,
        `forum_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `patron` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `organisation_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `contact_email` varchar(255),
        `description` varchar(255),
        `name_inventor` varchar(255),
        `open_source` bit not null,
        `stars` integer not null,
        `title` varchar(255),
        `web_site` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `contact_email` varchar(255),
        `description` varchar(255),
        `name_inventor` varchar(255),
        `open_source` bit not null,
        `stars` integer not null,
        `title` varchar(255),
        `web_site` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `work_programme` (
       `id` integer not null,
        `version` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXj1shjic6mip5nyik4ywhvxiid on `application` (`ticker`);
create index IDX618is0hf6jk8mhi0qeume2hqw on `application` (`creation_moment`);

    alter table `application` 
       add constraint UK_ao7wxw7e7mkj6g5q49yq2fw8d unique (`ticker`);
create index IDXkdedh59d4nlotef0ugh1ky8qm on `credit_card` (`month_expired`);
create index IDX8y5dhdokiy08xsb1smd6k1fgj on `credit_card` (`year_expired`);

    alter table `investment_round` 
       add constraint UK_408l1ohatdkkut5bkt0eu6ifs unique (`ticker`);
create index IDXr7lyttb4bnfd85oud954xkpx7 on `technology_record` (`activity_sector`);
create index IDX1e3bnrgq94pm3lol85yf69dpk on `technology_record` (`stars`);
create index IDX7rwm294nfcoq797e0l99lux40 on `tool_record` (`activity_sector`);
create index IDXs6ga4e8wd3ygn3b5o4l14q6ti on `tool_record` (`stars`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting_record` 
       add constraint `FK41jm4vk7runvmg5tderffrele` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting_record` 
       add constraint `FKk1pmfnppwk0kav7xloy8u71uq` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `activity` 
       add constraint `FK1ufotopeofii4jlefyk9c7os5` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKk5ibe41quxsif8im882xv4afo` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `application` 
       add constraint `FKl4fp0cd8c008ma79n6w58xhk9` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `banner` 
       add constraint `FKdocr1jjfgwx9ef5jbf675l360` 
       foreign key (`patron_id`) 
       references `patron` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper_request` 
       add constraint `FKrkmyfaktfktoo2v26a9qu4ebb` 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `credit_card` 
       add constraint `FKa4pbn9v8sv66p46fsrke8ow89` 
       foreign key (`banner_id`) 
       references `banner` (`id`);

    alter table `credit_card` 
       add constraint `FK31e9eqi896koc93q7yjs5yoox` 
       foreign key (`patron_id`) 
       references `patron` (`id`);

    alter table `entrepreneur` 
       add constraint FK_r6tqltqvrlh1cyy8rsj5pev1q 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `forum` 
       add constraint `FK49evxvl11kdqxjybm2sn41x6f` 
       foreign key (`creator_id`) 
       references `authenticated` (`id`);

    alter table `forum` 
       add constraint `FKq8ggcjgl5by5gf6l5bji632hu` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `investment_round` 
       add constraint `FKkj1l8c2ftn9c65y061me6t37j` 
       foreign key (`entrepreneur_id`) 
       references `entrepreneur` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FKd8wmf6nghttk2h9gq7v8p9lqo` 
       foreign key (`creator_id`) 
       references `authenticated` (`id`);

    alter table `message` 
       add constraint `FKfwwpivgx5j4vw4594dgrw884q` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `participant` 
       add constraint `FK80gruu22vbyiojed5sawtqc6a` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `participant` 
       add constraint `FK17hovwcvdf6h03yygtp7wlrku` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `patron` 
       add constraint FK_8xx5nujhuio3advxc2freyu65 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
