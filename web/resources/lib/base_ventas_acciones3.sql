/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     1/11/2019 15:52:43                           */
/*==============================================================*/


drop index ACCIONISTA_ACCIONES_FK;

drop index ACCION_PK;

drop table ACCION;

drop index ACCIONISTA_VENTAS_FK;

drop index ACCION_HISTORICOS_VENTAS_FK;

drop index HISTORICO_VENTAS_PK;

drop table HISTORICO_VENTAS;

drop index METODO_PAGO_PK;

drop table METODO_PAGO;

drop index RELATIONSHIP_9_FK;

drop index RELATIONSHIP_8_FK;

drop index METODO_PAGO_USUARIOS_PK;

drop table METODO_PAGO_USUARIOS;

drop index TIPO_USUARIO_PK;

drop table TIPO_USUARIO;

drop index TIPOACCIONISTA_FK;

drop index USUARIO_PK;

drop table USUARIO;

/*==============================================================*/
/* Table: ACCION                                                */
/*==============================================================*/
create table ACCION (
   ID_ACCION            SERIAL               not null,
   ID_USUARIO           INT4                 not null,
   DESCRIPCION          VARCHAR(255)         null,
   ESTADO_ACCION        BOOL                 not null,
   VALOR_PORCENTUAL     DECIMAL(10,2)        not null,
   VALOR_NOMINAL        DECIMAL(10,2)        not null,
   constraint PK_ACCION primary key (ID_ACCION)
);

/*==============================================================*/
/* Index: ACCION_PK                                             */
/*==============================================================*/
create unique index ACCION_PK on ACCION (
ID_ACCION
);

/*==============================================================*/
/* Index: ACCIONISTA_ACCIONES_FK                                */
/*==============================================================*/
create  index ACCIONISTA_ACCIONES_FK on ACCION (
ID_USUARIO
);

/*==============================================================*/
/* Table: HISTORICO_VENTAS                                      */
/*==============================================================*/
create table HISTORICO_VENTAS (
   ID_VENTAS            SERIAL               not null,
   ID_ACCION            INT4                 null,
   ID_USUARIO           INT4                 null,
   FECHA_VENTA          DATE                 not null,
   ESTADO_ACTUAL        INT4                 not null,
   VALOR_VENTA          DECIMAL(10,2)        not null,
   VALOR_REAL           DECIMAL(10,2)        not null,
   constraint PK_HISTORICO_VENTAS primary key (ID_VENTAS)
);

/*==============================================================*/
/* Index: HISTORICO_VENTAS_PK                                   */
/*==============================================================*/
create unique index HISTORICO_VENTAS_PK on HISTORICO_VENTAS (
ID_VENTAS
);

/*==============================================================*/
/* Index: ACCION_HISTORICOS_VENTAS_FK                           */
/*==============================================================*/
create  index ACCION_HISTORICOS_VENTAS_FK on HISTORICO_VENTAS (
ID_ACCION
);

/*==============================================================*/
/* Index: ACCIONISTA_VENTAS_FK                                  */
/*==============================================================*/
create  index ACCIONISTA_VENTAS_FK on HISTORICO_VENTAS (
ID_USUARIO
);

/*==============================================================*/
/* Table: METODO_PAGO                                           */
/*==============================================================*/
create table METODO_PAGO (
   ID_METODO_PAGO       SERIAL               not null,
   DESCRIPCION          VARCHAR(255)         not null,
   constraint PK_METODO_PAGO primary key (ID_METODO_PAGO)
);

/*==============================================================*/
/* Index: METODO_PAGO_PK                                        */
/*==============================================================*/
create unique index METODO_PAGO_PK on METODO_PAGO (
ID_METODO_PAGO
);

/*==============================================================*/
/* Table: METODO_PAGO_USUARIOS                                  */
/*==============================================================*/
create table METODO_PAGO_USUARIOS (
   ID_METODO_PAGO       SERIAL               not null,
   ID_USUARIO           INT4                 not null,
   IDENTIFICADOR        VARCHAR(30)          not null,
   CLAVE                VARCHAR(12)          null,
   constraint PK_METODO_PAGO_USUARIOS primary key (ID_METODO_PAGO, ID_USUARIO)
);

/*==============================================================*/
/* Index: METODO_PAGO_USUARIOS_PK                               */
/*==============================================================*/
create unique index METODO_PAGO_USUARIOS_PK on METODO_PAGO_USUARIOS (
ID_METODO_PAGO,
ID_USUARIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_8_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_8_FK on METODO_PAGO_USUARIOS (
ID_USUARIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_9_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_9_FK on METODO_PAGO_USUARIOS (
ID_METODO_PAGO
);

/*==============================================================*/
/* Table: TIPO_USUARIO                                          */
/*==============================================================*/
create table TIPO_USUARIO (
   ID_TIPOUSUARIO       SERIAL               not null,
   DESCRIPCION          VARCHAR(100)         not null,
   constraint PK_TIPO_USUARIO primary key (ID_TIPOUSUARIO)
);

/*==============================================================*/
/* Index: TIPO_USUARIO_PK                                       */
/*==============================================================*/
create unique index TIPO_USUARIO_PK on TIPO_USUARIO (
ID_TIPOUSUARIO
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   ID_USUARIO           SERIAL               not null,
   ID_TIPOUSUARIO       INT4                 null,
   CI_RUC               VARCHAR(13)          not null,
   NOMBRE               VARCHAR(255)         not null,
   CAPITAL              DECIMAL(10,2)        null,
   CORREO               VARCHAR(255)         not null,
   CLAVE                VARCHAR(25)          not null,
   VALOR_EMPRESA        DECIMAL(10,2)        null,
   constraint PK_USUARIO primary key (ID_USUARIO)
);

/*==============================================================*/
/* Index: USUARIO_PK                                            */
/*==============================================================*/
create unique index USUARIO_PK on USUARIO (
ID_USUARIO
);

/*==============================================================*/
/* Index: TIPOACCIONISTA_FK                                     */
/*==============================================================*/
create  index TIPOACCIONISTA_FK on USUARIO (
ID_TIPOUSUARIO
);

alter table ACCION
   add constraint FK_ACCION_ACCIONIST_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table HISTORICO_VENTAS
   add constraint FK_HISTORIC_ACCIONIST_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table HISTORICO_VENTAS
   add constraint FK_HISTORIC_ACCION_HI_ACCION foreign key (ID_ACCION)
      references ACCION (ID_ACCION)
      on delete restrict on update restrict;

alter table METODO_PAGO_USUARIOS
   add constraint FK_METODO_P_RELATIONS_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table METODO_PAGO_USUARIOS
   add constraint FK_METODO_P_RELATIONS_METODO_P foreign key (ID_METODO_PAGO)
      references METODO_PAGO (ID_METODO_PAGO)
      on delete restrict on update restrict;

alter table USUARIO
   add constraint FK_USUARIO_TIPOACCIO_TIPO_USU foreign key (ID_TIPOUSUARIO)
      references TIPO_USUARIO (ID_TIPOUSUARIO)
      on delete restrict on update restrict;

