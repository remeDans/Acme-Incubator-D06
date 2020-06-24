
    alter table `accounting_record` 
       drop 
       foreign key `FK41jm4vk7runvmg5tderffrele`;

    alter table `accounting_record` 
       drop 
       foreign key `FKk1pmfnppwk0kav7xloy8u71uq`;

    alter table `activity` 
       drop 
       foreign key `FK1ufotopeofii4jlefyk9c7os5`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKk5ibe41quxsif8im882xv4afo`;

    alter table `application` 
       drop 
       foreign key `FKl4fp0cd8c008ma79n6w58xhk9`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `banner` 
       drop 
       foreign key `FKdocr1jjfgwx9ef5jbf675l360`;

    alter table `bookkeeper` 
       drop 
       foreign key FK_krvjp9eaqyapewl2igugbo9o8;

    alter table `bookkeeper_request` 
       drop 
       foreign key `FKrkmyfaktfktoo2v26a9qu4ebb`;

    alter table `credit_card` 
       drop 
       foreign key `FKa4pbn9v8sv66p46fsrke8ow89`;

    alter table `credit_card` 
       drop 
       foreign key `FK31e9eqi896koc93q7yjs5yoox`;

    alter table `entrepreneur` 
       drop 
       foreign key FK_r6tqltqvrlh1cyy8rsj5pev1q;

    alter table `forum` 
       drop 
       foreign key `FK49evxvl11kdqxjybm2sn41x6f`;

    alter table `forum` 
       drop 
       foreign key `FKq8ggcjgl5by5gf6l5bji632hu`;

    alter table `investment_round` 
       drop 
       foreign key `FKkj1l8c2ftn9c65y061me6t37j`;

    alter table `investor` 
       drop 
       foreign key FK_dcek5rr514s3rww0yy57vvnpq;

    alter table `message` 
       drop 
       foreign key `FKd8wmf6nghttk2h9gq7v8p9lqo`;

    alter table `message` 
       drop 
       foreign key `FKfwwpivgx5j4vw4594dgrw884q`;

    alter table `participant` 
       drop 
       foreign key `FK80gruu22vbyiojed5sawtqc6a`;

    alter table `participant` 
       drop 
       foreign key `FK17hovwcvdf6h03yygtp7wlrku`;

    alter table `patron` 
       drop 
       foreign key FK_8xx5nujhuio3advxc2freyu65;

    drop table if exists `accounting_record`;

    drop table if exists `activity`;

    drop table if exists `administrator`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `bookkeeper`;

    drop table if exists `bookkeeper_request`;

    drop table if exists `challenge`;

    drop table if exists `credit_card`;

    drop table if exists `customisation`;

    drop table if exists `dans_bulletin`;

    drop table if exists `entrepreneur`;

    drop table if exists `forum`;

    drop table if exists `inquiry`;

    drop table if exists `investment_round`;

    drop table if exists `investor`;

    drop table if exists `message`;

    drop table if exists `notice`;

    drop table if exists `overture`;

    drop table if exists `participant`;

    drop table if exists `patron`;

    drop table if exists `technology_record`;

    drop table if exists `tool_record`;

    drop table if exists `user_account`;

    drop table if exists `work_programme`;

    drop table if exists `hibernate_sequence`;
