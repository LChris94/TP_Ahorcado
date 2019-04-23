# TP AHORCADO
### Diferencia entre Runnable y Theread
Hay dos maneras de crear una instancia de thread:
- Una es declarar una clase que sea subclase de Thread. Esta subclase debe sobrescribir el metodo run() de la clase Thread. La instancia de la subclase puede ser declarada e inicializada.
- 	La otra manera para crear un hilo es declarar una clase que implemente la interfaz Runnable. Esta clase implementa el metodo run. La instancia de la clase puede ser declarada, pasando una argunmento cuando crea el hilo y lo inicia.
