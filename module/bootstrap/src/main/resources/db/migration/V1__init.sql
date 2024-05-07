create table users
(
    id varchar(27) not null primary key,
    created_date datetime(6)  not null,
    updated_at datetime(6)  not null,
    display_id varchar(40) not null,
    name varchar(20) not null,
    constraint UK_display_id unique (display_id)
);

create table user_role
(
    user_id varchar(255) not null,
    roles   enum ('NEED_SIGNUP', 'USER') not null,
    constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users (id)
);

create table user_social_account
(
    provider enum ('GOOGLE', 'GITHUB', 'KAKAO') not null,
    social_id varchar(30) not null,
    created_date datetime(6) not null,
    user_id varchar(27) not null,
    primary key (provider, social_id)
);

create table user_token_entity
(
    user_id varchar(255) not null,
    token varchar(255) not null,
    primary key (token, user_id)
);

create table singleton_profile_entity
(
    id bigint auto_increment primary key,
    name varchar(20) not null,
    profile_image_url varchar(255) not null,
    description varchar(100) null,
    email varchar(50) null,
    location varchar(100) null,
    organization varchar(20) null,
    read_me text null
);

create table link_tree
(
    profile_id bigint not null,
    link_tree  varchar(255) not null,
    constraint FK_profile_id foreign key (profile_id) references singleton_profile_entity (id)
);
