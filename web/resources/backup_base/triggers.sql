CREATE FUNCTION trigger_hv() returns Trigger
as
$$
begin

insert into historico_ventas(id_accion,id_usuario,
fecha_venta,estado_actual,valor_venta,valor_real,cantidad,id_empresa) 
values(new.id_accion,new.id_usuario,CURRENT_DATE,1,new.valor_nominal,new.valor_real,new.cantidad,new.id_empresa);

return new;
end
$$
Language plpgsql;

DROP TRIGGER IF EXISTS TR_insert_accion
  ON public.accion;
CREATE TRIGGER TR_insert_accion 
AFTER INSERT OR UPDATE ON accion 
FOR EACH ROW
EXECUTE PROCEDURE trigger_hv();