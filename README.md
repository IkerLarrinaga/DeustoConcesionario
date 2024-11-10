El proyecto consiste en un concesionario, tiene un apartado de Cliente y uno de Trabajador, teniendo cada uno funciones específicas.

En la parte cliente, la aplicación tiene un catálogo de coches, pudiendo seleccionarlos, para después comprarlo o alquilarlo, también tiene un buscador para elegir características específicas.



La clase principal es: **src/main/Main.java**

Las credenciales para iniciar sesión son:
  - Cliente:
        - Email: rcarballedo@deusto.es
        - Contraseña: roberto 
  - Trabajador:
        - Email: unai.aguilera@deusto.es
        - Contraseña: unai

Tiene dos ficheros de configuración:

**- resource/data/registro.txt** (fichero de configuración de usuarios)

El fichero tiene los siguientes campos:

  - Nombre                               # Nombre del Cliente/Trabajador
  - Primer apellido                      # Primer apellido del Cliente/Trabajador
  - Segundo apellido                     # Segundo apellido del Cliente/Trabajador
  - Fecha de nacimiento                  # Fecha de nacimiento del Cliente/Trabajador
  - DNI                                  # DNI del Cliente/Trabajador
  - Email                                # Email del Cliente/Trabajador, necesario para el inicio de sesión
  - Contraseña                           # Contraseña del Cliente/Trabajador, elegida para hacer el inicio de sesión
  - Rol                                  # Rol del Cliente/Trabajador, siendo 1 para cliente y 2 para trabajador

**- resources/data/vehiculos.txt** (fichero con el listado de vehículos disponibles)
