# Sistema de Gestión - Proyecto en Java

## Descripción del Proyecto

Aplicación de escritorio desarrollada en Java para la gestión de inventario, usuarios y emisión de documentos comerciales. El sistema opera con una base de datos local embebida y permite la exportación de reportes en múltiples formatos.

## Tecnologías y Entorno

* **Lenguaje:** Java
* **Entorno de Desarrollo:** Apache NetBeans
* **Base de Datos:** SQLite
* **Interfaz Gráfica:** Java Swing

## Dependencias

El proyecto incluye las siguientes librerías externas ubicadas en el código fuente:

* **SQLite JDBC:** Para la conexión directa con el archivo baseDeDatos.db.
* **iText5:** Para la generación de reportes y exportación de comprobantes en PDF.
* **Apache POI:** Para la exportación y lectura de datos en formato Excel.
* **JFreeChart y JCommon:** Para la renderización de gráficos avanzados en la interfaz de usuario.

## Estructura de Paquetes Principales

* **GestorDatosPermanentes:** Clases de conexión y ejecución de consultas SQL mediante SQLiteManager.
* **DocumentoComercial:** Lógica de negocio para Facturas, Boletas, Proformas y Comprobantes.
* **MenuDinamico:** Controladores de vistas, modelos de tablas estandarizados y utilidades como GeneradorPDF.
* **Excel:** Módulo dedicado a la exportación de datos mediante ExportadorExcel.
* **Login:** Interfaz de autenticación y validación de credenciales.
* **Producto y Usuario:** Clases de entidad que representan el modelo de datos del sistema.

## Instrucciones de Ejecución

1. Clonar o descargar el repositorio en el equipo local.
2. Abrir el proyecto directamente desde Apache NetBeans.
3. Verificar que las librerías incluidas en las carpetas de recursos estén correctamente enlazadas en las propiedades del proyecto.
4. Ejecutar el archivo FrmLogin.java como clase principal para iniciar la aplicación.
