--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-11-19 10:14:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 208 (class 1255 OID 24806)
-- Name: trigger_hv(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.trigger_hv() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin

insert into historico_ventas(id_accion,id_usuario,
fecha_venta,estado_actual,valor_venta,valor_real,cantidad,id_empresa) 
values(new.id_accion,new.id_usuario,CURRENT_DATE,1,new.valor_nominal,new.valor_real,new.cantidad,new.id_empresa);

return new;
end
$$;


ALTER FUNCTION public.trigger_hv() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 24701)
-- Name: accion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accion (
    id_accion integer NOT NULL,
    id_usuario integer NOT NULL,
    descripcion character varying(255),
    estado_accion boolean NOT NULL,
    valor_porcentual numeric(10,2) NOT NULL,
    valor_nominal numeric(10,2) NOT NULL,
    cantidad integer,
    id_empresa integer,
    valor_real numeric(10,2)
);


ALTER TABLE public.accion OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 24699)
-- Name: accion_id_accion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.accion_id_accion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.accion_id_accion_seq OWNER TO postgres;

--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 196
-- Name: accion_id_accion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.accion_id_accion_seq OWNED BY public.accion.id_accion;


--
-- TOC entry 199 (class 1259 OID 24711)
-- Name: historico_ventas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historico_ventas (
    id_ventas integer NOT NULL,
    id_accion integer,
    id_usuario integer,
    fecha_venta date NOT NULL,
    estado_actual integer NOT NULL,
    valor_venta numeric(10,2) NOT NULL,
    valor_real numeric(10,2) NOT NULL,
    cantidad integer,
    id_empresa integer
);


ALTER TABLE public.historico_ventas OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 24709)
-- Name: historico_ventas_id_ventas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.historico_ventas_id_ventas_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.historico_ventas_id_ventas_seq OWNER TO postgres;

--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 198
-- Name: historico_ventas_id_ventas_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.historico_ventas_id_ventas_seq OWNED BY public.historico_ventas.id_ventas;


--
-- TOC entry 201 (class 1259 OID 24722)
-- Name: metodo_pago; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.metodo_pago (
    id_metodo_pago integer NOT NULL,
    descripcion character varying(255) NOT NULL
);


ALTER TABLE public.metodo_pago OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 24720)
-- Name: metodo_pago_id_metodo_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.metodo_pago_id_metodo_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.metodo_pago_id_metodo_pago_seq OWNER TO postgres;

--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 200
-- Name: metodo_pago_id_metodo_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.metodo_pago_id_metodo_pago_seq OWNED BY public.metodo_pago.id_metodo_pago;


--
-- TOC entry 203 (class 1259 OID 24731)
-- Name: metodo_pago_usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.metodo_pago_usuarios (
    id_metodo_pago integer NOT NULL,
    id_usuario integer NOT NULL,
    identificador character varying(30) NOT NULL,
    clave character varying(12)
);


ALTER TABLE public.metodo_pago_usuarios OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 24729)
-- Name: metodo_pago_usuarios_id_metodo_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.metodo_pago_usuarios_id_metodo_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.metodo_pago_usuarios_id_metodo_pago_seq OWNER TO postgres;

--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 202
-- Name: metodo_pago_usuarios_id_metodo_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.metodo_pago_usuarios_id_metodo_pago_seq OWNED BY public.metodo_pago_usuarios.id_metodo_pago;


--
-- TOC entry 205 (class 1259 OID 24742)
-- Name: tipo_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_usuario (
    id_tipousuario integer NOT NULL,
    descripcion character varying(100) NOT NULL
);


ALTER TABLE public.tipo_usuario OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 24740)
-- Name: tipo_usuario_id_tipousuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_usuario_id_tipousuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_usuario_id_tipousuario_seq OWNER TO postgres;

--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 204
-- Name: tipo_usuario_id_tipousuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_usuario_id_tipousuario_seq OWNED BY public.tipo_usuario.id_tipousuario;


--
-- TOC entry 207 (class 1259 OID 24751)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    id_tipousuario integer,
    ci_ruc character varying(13) NOT NULL,
    nombre character varying(255) NOT NULL,
    capital numeric(10,2),
    correo character varying(255) NOT NULL,
    clave character varying(25) NOT NULL,
    valor_empresa numeric(10,2)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24749)
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_usuario_seq OWNER TO postgres;

--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 206
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- TOC entry 2717 (class 2604 OID 24704)
-- Name: accion id_accion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accion ALTER COLUMN id_accion SET DEFAULT nextval('public.accion_id_accion_seq'::regclass);


--
-- TOC entry 2718 (class 2604 OID 24714)
-- Name: historico_ventas id_ventas; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_ventas ALTER COLUMN id_ventas SET DEFAULT nextval('public.historico_ventas_id_ventas_seq'::regclass);


--
-- TOC entry 2719 (class 2604 OID 24725)
-- Name: metodo_pago id_metodo_pago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metodo_pago ALTER COLUMN id_metodo_pago SET DEFAULT nextval('public.metodo_pago_id_metodo_pago_seq'::regclass);


--
-- TOC entry 2720 (class 2604 OID 24734)
-- Name: metodo_pago_usuarios id_metodo_pago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metodo_pago_usuarios ALTER COLUMN id_metodo_pago SET DEFAULT nextval('public.metodo_pago_usuarios_id_metodo_pago_seq'::regclass);


--
-- TOC entry 2721 (class 2604 OID 24745)
-- Name: tipo_usuario id_tipousuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_usuario ALTER COLUMN id_tipousuario SET DEFAULT nextval('public.tipo_usuario_id_tipousuario_seq'::regclass);


--
-- TOC entry 2722 (class 2604 OID 24754)
-- Name: usuario id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- TOC entry 2882 (class 0 OID 24701)
-- Dependencies: 197
-- Data for Name: accion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accion (id_accion, id_usuario, descripcion, estado_accion, valor_porcentual, valor_nominal, cantidad, id_empresa, valor_real) FROM stdin;
35	10	Accion para beneficencia	t	4.50	12.00	2	9	12.00
34	9	Accion para beneficencia	t	4.50	12.00	2	9	12.00
36	10	Accion para beneficencia	t	4.50	12.00	0	9	15.09
37	12	Accion para beneficencia	t	4.50	12.00	1	9	24.27
38	9	Este es un ejemplo	t	2.80	10.50	5	9	10.50
39	9	hs	t	1.80	2.50	3	9	2.50
40	13	Una accion de ejemplo	t	2.30	2.50	2	13	2.50
41	14	Una accion de ejemplo	t	2.30	2.50	0	13	2.59
42	10	Una accion de ejemplo	t	2.30	2.50	2	13	2.98
\.


--
-- TOC entry 2884 (class 0 OID 24711)
-- Dependencies: 199
-- Data for Name: historico_ventas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historico_ventas (id_ventas, id_accion, id_usuario, fecha_venta, estado_actual, valor_venta, valor_real, cantidad, id_empresa) FROM stdin;
12	34	9	2019-11-13	1	12.00	12.00	5	9
13	34	9	2019-11-13	1	12.00	12.00	3	9
14	35	10	2019-11-13	1	12.00	12.00	2	9
15	34	9	2019-11-13	1	12.00	12.00	2	9
16	36	10	2019-11-13	1	12.00	15.09	1	9
17	36	10	2019-11-13	1	12.00	15.09	0	9
18	37	12	2019-11-13	1	12.00	24.27	1	9
19	38	9	2019-11-13	1	10.50	10.50	5	9
20	39	9	2019-11-13	1	2.50	2.50	3	9
21	40	13	2019-11-13	1	2.50	2.50	4	13
22	40	13	2019-11-13	1	2.50	2.50	2	13
23	41	14	2019-11-13	1	2.50	2.59	2	13
24	41	14	2019-11-13	1	2.50	2.59	0	13
25	42	10	2019-11-13	1	2.50	2.98	2	13
\.


--
-- TOC entry 2886 (class 0 OID 24722)
-- Dependencies: 201
-- Data for Name: metodo_pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.metodo_pago (id_metodo_pago, descripcion) FROM stdin;
1	Tarjeta de Credito
2	Paypal
\.


--
-- TOC entry 2888 (class 0 OID 24731)
-- Dependencies: 203
-- Data for Name: metodo_pago_usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.metodo_pago_usuarios (id_metodo_pago, id_usuario, identificador, clave) FROM stdin;
1	10	5555-1231-1231-1244	\N
2	12	christianrualed@gmail.com	\N
1	14	1111-1111-1111-1111	\N
\.


--
-- TOC entry 2890 (class 0 OID 24742)
-- Dependencies: 205
-- Data for Name: tipo_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_usuario (id_tipousuario, descripcion) FROM stdin;
1	Empresa
2	Persona Natural
\.


--
-- TOC entry 2892 (class 0 OID 24751)
-- Dependencies: 207
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id_usuario, id_tipousuario, ci_ruc, nombre, capital, correo, clave, valor_empresa) FROM stdin;
8	1	1729009912001	Acerocenter	\N	acerocenter@acerocenter.com	acerocenter	\N
9	1	1728221921001	UPS	\N	ups@ups.edu.ec	ups	\N
10	2	1728881282	Christian Ruales	\N	cruales@gmail.com	cruales	\N
11	2	17226352172	Fabian Rosero	\N	fb	fb	\N
12	2	1827336212	Estuardo Sanchiguano	\N	es@gmail.com	es	\N
13	1	1722663366001	Empresa	\N	empresa@empresa.com	empresa	\N
14	2	1722884155	Persona	\N	persona@gmail.com	persona	\N
\.


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 196
-- Name: accion_id_accion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.accion_id_accion_seq', 42, true);


--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 198
-- Name: historico_ventas_id_ventas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.historico_ventas_id_ventas_seq', 25, true);


--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 200
-- Name: metodo_pago_id_metodo_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.metodo_pago_id_metodo_pago_seq', 2, true);


--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 202
-- Name: metodo_pago_usuarios_id_metodo_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.metodo_pago_usuarios_id_metodo_pago_seq', 1, false);


--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 204
-- Name: tipo_usuario_id_tipousuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_usuario_id_tipousuario_seq', 2, true);


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 206
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 14, true);


--
-- TOC entry 2726 (class 2606 OID 24706)
-- Name: accion pk_accion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accion
    ADD CONSTRAINT pk_accion PRIMARY KEY (id_accion);


--
-- TOC entry 2731 (class 2606 OID 24716)
-- Name: historico_ventas pk_historico_ventas; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_ventas
    ADD CONSTRAINT pk_historico_ventas PRIMARY KEY (id_ventas);


--
-- TOC entry 2734 (class 2606 OID 24727)
-- Name: metodo_pago pk_metodo_pago; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metodo_pago
    ADD CONSTRAINT pk_metodo_pago PRIMARY KEY (id_metodo_pago);


--
-- TOC entry 2737 (class 2606 OID 24736)
-- Name: metodo_pago_usuarios pk_metodo_pago_usuarios; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metodo_pago_usuarios
    ADD CONSTRAINT pk_metodo_pago_usuarios PRIMARY KEY (id_metodo_pago, id_usuario);


--
-- TOC entry 2741 (class 2606 OID 24747)
-- Name: tipo_usuario pk_tipo_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_usuario
    ADD CONSTRAINT pk_tipo_usuario PRIMARY KEY (id_tipousuario);


--
-- TOC entry 2744 (class 2606 OID 24759)
-- Name: usuario pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (id_usuario);


--
-- TOC entry 2747 (class 2606 OID 24810)
-- Name: usuario unique_ci; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT unique_ci UNIQUE (ci_ruc);


--
-- TOC entry 2749 (class 2606 OID 24793)
-- Name: usuario unique_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT unique_email UNIQUE (correo);


--
-- TOC entry 2727 (class 1259 OID 24718)
-- Name: accion_historicos_ventas_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX accion_historicos_ventas_fk ON public.historico_ventas USING btree (id_accion);


--
-- TOC entry 2723 (class 1259 OID 24707)
-- Name: accion_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX accion_pk ON public.accion USING btree (id_accion);


--
-- TOC entry 2724 (class 1259 OID 24708)
-- Name: accionista_acciones_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX accionista_acciones_fk ON public.accion USING btree (id_usuario);


--
-- TOC entry 2728 (class 1259 OID 24719)
-- Name: accionista_ventas_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX accionista_ventas_fk ON public.historico_ventas USING btree (id_usuario);


--
-- TOC entry 2729 (class 1259 OID 24717)
-- Name: historico_ventas_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX historico_ventas_pk ON public.historico_ventas USING btree (id_ventas);


--
-- TOC entry 2732 (class 1259 OID 24728)
-- Name: metodo_pago_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX metodo_pago_pk ON public.metodo_pago USING btree (id_metodo_pago);


--
-- TOC entry 2735 (class 1259 OID 24737)
-- Name: metodo_pago_usuarios_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX metodo_pago_usuarios_pk ON public.metodo_pago_usuarios USING btree (id_metodo_pago, id_usuario);


--
-- TOC entry 2738 (class 1259 OID 24738)
-- Name: relationship_8_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX relationship_8_fk ON public.metodo_pago_usuarios USING btree (id_usuario);


--
-- TOC entry 2739 (class 1259 OID 24739)
-- Name: relationship_9_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX relationship_9_fk ON public.metodo_pago_usuarios USING btree (id_metodo_pago);


--
-- TOC entry 2742 (class 1259 OID 24748)
-- Name: tipo_usuario_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX tipo_usuario_pk ON public.tipo_usuario USING btree (id_tipousuario);


--
-- TOC entry 2745 (class 1259 OID 24761)
-- Name: tipoaccionista_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX tipoaccionista_fk ON public.usuario USING btree (id_tipousuario);


--
-- TOC entry 2750 (class 1259 OID 24760)
-- Name: usuario_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX usuario_pk ON public.usuario USING btree (id_usuario);


--
-- TOC entry 2759 (class 2620 OID 24808)
-- Name: accion tr_insert_accion; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_insert_accion AFTER INSERT OR UPDATE ON public.accion FOR EACH ROW EXECUTE PROCEDURE public.trigger_hv();


--
-- TOC entry 2751 (class 2606 OID 24762)
-- Name: accion fk_accion_accionist_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accion
    ADD CONSTRAINT fk_accion_accionist_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2752 (class 2606 OID 24794)
-- Name: accion fk_accion_empresa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accion
    ADD CONSTRAINT fk_accion_empresa FOREIGN KEY (id_empresa) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 2754 (class 2606 OID 24772)
-- Name: historico_ventas fk_historic_accion_hi_accion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_ventas
    ADD CONSTRAINT fk_historic_accion_hi_accion FOREIGN KEY (id_accion) REFERENCES public.accion(id_accion) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2755 (class 2606 OID 24801)
-- Name: historico_ventas fk_historic_accion_hi_empresa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_ventas
    ADD CONSTRAINT fk_historic_accion_hi_empresa FOREIGN KEY (id_empresa) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 2753 (class 2606 OID 24767)
-- Name: historico_ventas fk_historic_accionist_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_ventas
    ADD CONSTRAINT fk_historic_accionist_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2757 (class 2606 OID 24782)
-- Name: metodo_pago_usuarios fk_metodo_p_relations_metodo_p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metodo_pago_usuarios
    ADD CONSTRAINT fk_metodo_p_relations_metodo_p FOREIGN KEY (id_metodo_pago) REFERENCES public.metodo_pago(id_metodo_pago) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2756 (class 2606 OID 24777)
-- Name: metodo_pago_usuarios fk_metodo_p_relations_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metodo_pago_usuarios
    ADD CONSTRAINT fk_metodo_p_relations_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2758 (class 2606 OID 24787)
-- Name: usuario fk_usuario_tipoaccio_tipo_usu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_usuario_tipoaccio_tipo_usu FOREIGN KEY (id_tipousuario) REFERENCES public.tipo_usuario(id_tipousuario) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2019-11-19 10:14:20

--
-- PostgreSQL database dump complete
--

