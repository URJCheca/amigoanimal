# Amigo Animal
## Descripcion:

Amigo Animal es una aplicacion que sirve como soporte a una tienda de mascotas fisica. La web tendra a disposicion de los visitantes el catalogo disponible en la tienda asi como ofrece la posibilidad de realizar compras online a clientes registrados en la plataforma (mas no se realiza envio a casa, es recogida en tienda). La compra de articulos supone la participacion en un sistema de puntos, gracias a  los cuales se pueden disponer de descuentos o productos gratuitos. De igual forma, la tienda fisica tiene un servicio de peluqueria/clinica veterinaria (falta por concretar). Mediante la web se puede concretar una cita para poder llevarlos clientes a las mascotas. Esto solo es posible si se encuentra registrado como cliente. De lo contrario es necesario llamar para concretar dicha cita.

## Entidades:

* Usuario: Los usuarios son aquellos quienes gestionan la pagina web. Existe el usuario "Cliente" que ya se encuentra registrado y dispone de una cuenta y el usuario "Invitado" que igual podra navegar por la pagina pero no podra realizar compras online ni concretar cita en la peluqueria/clinica veterinaria. Tambien habra un usuario "Trabajador" que basicamente dara de alta baja a los productos disponible en el catalogo
* Producto: Todos los posibles productos ofertados en la pagina. Disponen de un nombre, descripcion, precio... asi como una opcion para añadirla al carrito. Si el usuario no esta logeado y pincha sobre el icono carrito se le enviara a la pagina de login
* Carrito: La lista de todos los articulos seleccionada por el usuario. No solo se encarga de listarlos y dar acceso al producto si se pincha en él, si no que tambien hace la sumatoria del precio de todos los productos
* Peluqueria/Veterinaria: Aun por determinar. La idea es que se puedan poner en contacto con ellos a fin de concretar una cita. Lo ideal seria la posibilidad que el usuario tuviera acceso a un calendario y poder sleccionar el mismo el dia y hora deseado entre un total de horas disponibles
* Mascotas: Las mascotas seran atendidas en las peluqueria/clinica veterinaria. Normalmente los clientes dejan que sus mascotas reciban el tratamiento y pasen a recogerlas mas tarde. POr ello la mascota tendra un dueño asociado, asi como rasgos identificacivos (Especie, raza, nombre, color...)

## Servicio Interno

## Integrantes

Alejandro Checa Sanchez-Isasi: URJCheca