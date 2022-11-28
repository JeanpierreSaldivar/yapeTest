# yapeTest
#El proyecto está hecho con la arquitectura MVI por módulos(tambien he trabajado en proyectos con MVVM y MVP), aplicando Clean Architecture y SOLID
Aquí deja un breve resumen de su concepto:

MVI significa M odel- V iew- I ntent :

M odel: en lugar de tener un estado separado para la vista, el modelo de vista y la capa de datos, el modelo será la única fuente de verdad del estado general y es inmutable para garantizar que se actualice desde un solo lugar.

V ista: representa la capa de UI, la actividad o el fragmento, define las acciones del usuario y representa el estado emitido por el modelo.

Intento : no lo confundas con el Intent de Android, es la intención de realizar una acción ya sea por parte del usuario o de la propia aplicación.

![Image text](https://miro.medium.com/max/720/0*5xaFEBxI6_zK6Xmn.png)

He aplicado la herramienta de DaggerHilt para la injección de dependencia (también he trabajado con Koin, pero no lo he aplicado en este proyecto), extension function, inline function, viewbinding (para este proyecto decidí no utilizar databinding, pero si he trabajado con esta herramienta), navigation Component y la herramienta de google maps.

Patrones de diseño aplicados Singleton, Adapter, Observer, Repository y entre otros.

Para los test unitarios de los casos de uso he utilizado JUnit4

Módulos:

-App: Aquí está mi MainActivity es el contenedor de los fragments, mi Aplication, mi ruta de navegación y mi App Module.

-core: Están todos los recursos del negocio y extensiones.

-data : Es la parte donde está la lógica de petición de los datos al servicio o datos locales, pero en este proyecto solo se trabaja con data remota.

-domain: Aquí es donde tengo mis casos de uso.

-domain-di: Aquí preparo mis casos de uso para luego ser injectado en los viewModel.

-features: Aquí tengo los módulos del negocio por pantallas, separados en dos: home y detail (El mapa lo he considerado dentro del modulo de detail). Aquí coloco por ejemplo mis fragments, viewmodel y otros componentes relacionados con la vista como: navigator,screenState, screenEvent, adapter y entre otros.
