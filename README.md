# Amigo Animal
## Descripcion:

Amigo Animal es una aplicacion que sirve como soporte a una tienda de mascotas fisica. La web tendra a disposicion de los visitantes el catalogo disponible en la tienda asi como ofrece la posibilidad de realizar compras online a clientes registrados en la plataforma (mas no se realiza envio a casa, es recogida en tienda). La compra de articulos supone la participacion en un sistema de puntos, gracias a  los cuales se pueden disponer de descuentos o productos gratuitos. De igual forma, la tienda fisica tiene un servicio de veterinaria . Mediante la web se puede concretar una cita para poder llevar los clientes a las mascotas. Esto solo es posible si se encuentra registrado como cliente. De lo contrario seria necesario llamar para concretar dicha cita o presentarse fisicamente.

## Entidades:

* Usuario: Los usuarios son aquellos quienes gestionan la pagina web. Existe el usuario "Cliente" que ya se encuentra registrado y dispone de una cuenta y el usuario "Invitado" que igual podra navegar por la pagina pero no podra realizar compras online. Tambien habra un usuario "Trabajador" que basicamente podra administrar los productos, mascotas y usuarios existentes en la aplicacion.
* Producto: Todos los posibles productos ofertados en la pagina. Disponen de un nombre, descripcion, precio... asi como una opcion para añadirla al carrito. 
* Clinica Veterinaria:Los trabajadores podran dar de alta o de baja a las mascotas en esta seccion, asi como mirar las ya registradas y filtrarlas de acuerdo a una serie de criterios
* Mascotas: Las mascotas seran atendidas en las clinica veterinaria. Normalmente los clientes dejan que sus mascotas reciban el tratamiento y pasen a recogerlas otro dia. Por ello la mascota tendra un dueño asociado, asi como rasgos identificacivos (Especie, raza, nombre, color...)


## Capturas
### Inicio
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Inicio.JPG"
     alt="Página de inicio"
     style="float: left; margin-right: 10px;" />
### Login
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Login.JPG"
     alt="Login de usuario"
     style="float: left; margin-right: 10px;" />
### Registro
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Registro.JPG"
     alt="Registro de usuarios"
     style="float: left; margin-right: 10px;" />
### TiendaAdmin
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/TiendaAdmin.JPG"
     alt="Tienda"
     style="float: left; margin-right: 10px;" />
### TiendaCliente
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/TiendaCliente.JPG"
     alt="Tienda"
     style="float: left; margin-right: 10px;" />
### Alta de productos
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Registrar_producto.JPG"
     alt="Registro de productos"
     style="float: left; margin-right: 10px;" />
### Busqueda de productos
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Busqueda_avanzada.JPG"
     alt="Busqueda avanzada de productos"
     style="float: left; margin-right: 10px;" />
### Carrito
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Carrito.JPG"
     alt="Carrito"
     style="float: left; margin-right: 10px;" />
### Confirmacion Compra
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/confirmacionCompra.JPG"
     alt="Confirmacion compra"
     style="float: left; margin-right: 10px;" />   
### Mensaje Enviado
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/MensajeEnviado.JPG"
     alt="Mensaje Enviado"
     style="float: left; margin-right: 10px;" />   
### Clinica
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Clinica_nueva.JPG"
     alt="Clinica veterinaria"
     style="float: left; margin-right: 10px;" />
### Alta de mascotas
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Alta%20Mascota.JPG"
     alt="Alta de mascotas"
     style="float: left; margin-right: 10px;" />
### Mascotas registradas
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Registro_mascotas.JPG"
     alt="Registro de mascotas"
     style="float: left; margin-right: 10px;" />
### Busqueda Usuario
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/busqueda_usuario.JPG"
     alt="Busqueda Usuario"
     style="float: left; margin-right: 10px;" />
### Resultado Busqueda Usuario
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/resultado_busqueda_usuario.JPG"
     alt="Resultado de la busqueda de usuario"
     style="float: left; margin-right: 10px;" />


     
## Diagrama Entidad-Relacion
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Entidad_relacion_.jpg"
     alt="Entidad-Relacion"
     style="float: left; margin-right: 10px;" />
     
## Diagram de clase y templates
<img src="https://github.com/URJCheca/amigoanimal/blob/master/Amigo%20animal/Diagramaclases_template.jpg"
     alt="Clases y templates"
     style="float: left; margin-right: 10px;" />
## Servicio Interno
 El servicio interno consiste en el envio automatico de una factura de los productos que se encuentran en el carrito y que el cliente indica para comprar. EL servicio recibe los productos, construye el mensaje y lo envia al mail del usuario.
## Integrantes

Alejandro Checa Sanchez-Isasi: URJCheca

Jose Antonio Medina Martinez: JosexAntonio
