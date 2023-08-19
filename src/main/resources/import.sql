INSERT INTO tareas(nombre, descripcion, estado) VALUES('Base de datos','Realizar un dashboard en PowerBI','completado')
INSERT INTO tareas(nombre, descripcion, estado) VALUES('Web Integral','Entrega del aplicativo final','completado')
INSERT INTO tareas(nombre, descripcion, estado) VALUES('BDispositivos inteligentes','Entrega del proyecto final','En progreso')

INSERT INTO roles(`id`,`nombre`) VALUES ( 1,'write'); 
INSERT INTO roles(`id`,`nombre`) VALUES ( 2,'read'); 

INSERT INTO usuarios(`id`,`apellido`,`email`,`enabled`,`nombre`,`password`,`username`) VALUES ( 1,'Mendoza','mariagmenr@gmail.com',1,'María','$2a$10$eqQaqSPfl.vjp88YJG8VuebEe8b6SrOf.pVrzB9JRqfvdAfApSqKO','mmendozar'); 


INSERT INTO usuarios_roles(`usuario_id`,`role_id`) VALUES ( '1','1'); 