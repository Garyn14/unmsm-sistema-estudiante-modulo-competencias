# Dashboard de Competencias

## Descripción

Este proyecto tiene como objetivo desarrollar un sistema de ayuda al estudiante, específicamente un módulo de dashboard de competencias. El alumno podrá visualizar un dashboard basado en sus competencias adquiridas a lo largo de sus estudios. El sistema está construido utilizando React para el frontend, Spring Boot para el backend, y MongoDB como base de datos. Los datos se entregan mediante archivos .csv que pasan por un proceso ETL antes de ser utilizados.

## Arquitectura

El proceso para obtener y visualizar los datos sigue la siguiente secuencia:

1. **Carga de Archivos .csv**: El docente sube los archivos .csv con los datos de los alumnos, cursos, notas, etc.
2. **Proceso ETL**:
    - **Extracción**: Los datos se extraen de los archivos .csv.
    - **Transformación**: Se eliminan columnas innecesarias y se limpian los datos.
    - **Carga**: Los datos transformados se cargan en MongoDB.
    - El proceso ETL se desarrollará utilizando Python.
3. **Creación de la Colección OLAP**: Se establece una colección solo de lectura en MongoDB para optimizar las consultas, mejorando el rendimiento ya que únicamente se realizan consultas y no inserciones.
4. **Backend**: Utilizando Spring Boot, se exponen APIs REST para interactuar con los datos.
5. **Frontend**: Se utiliza React para consumir las APIs y visualizar el dashboard de competencias.

## Tecnologías

- **Frontend**: 
  - React
  - Axios
  - Bootstrap
  - Chart.js
  - D3
  - React Bootstrap
  - React Chart.js 2
  - React Router DOM
  - FontAwesome

- **Backend**: 
  - Spring Boot
  - Spring Data
  - Spring Web

- **Base de Datos**: 
  - MongoDB

- **ETL**:
  - Python

- **Otros**:
  - Maven (para la gestión de dependencias en el backend)
  - npm (para la gestión de dependencias en el frontend)
