# Redes de Ordenadores - cliente #

Este es el repositorio de la parte del cliente del proyecto de Redes de Ordenadores. Se trata de un programa en Java 1.7 que usarán los médicos para consultar el estado de los pacientes. Podrán acceder al histórico de sus datos y a los actuales, y realizar mediciones remotas.

## Lógica del cliente ##
Habrá una clase cliente que tendra metodos para enviar datos y recibir datos del servidor, pero no se utilizarán directamente esos metodos. Se utilizarán los métodos de la clase Patient que implementa un interface Loader (para enviar datos a todos los componentes que implementen el interface Loadable), cada uno de los metodos esta encapsulado en un hilo distinto para no bloquear el hilo principal del programa. Una vez haya recibido los datos del servidor se ejecuta el método notifyLoadables y envia los datos al componente.