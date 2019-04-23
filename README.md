# TP AHORCADO
### Diferencia entre Runnable y Theread
- Runnable

Runnable es sólo una interfaz que necesita para instanciar un hilo para contenerlo. Mientras que el hilo contiene ya la capacidad de generar un hilo. Si usted extiende el hilo usted no puede extender cualquier cosa (Java no admite la herencia múltiple). Puede tener múltiples interfaces en una clase, por lo tanto podría tener Runnable.

Además, cuando extiende la clase Thread, cada subproceso crea un objeto único y se asocia con él. Cuando implementa Runnable, comparte el mismo objeto con varios subprocesos.

Esta es una interfaz que implementar, en la implementación de poner la lógica que desea ejecutar en algún hilo. También puede utilizar Runnable en lugares no relacionados con hilos. Un montón de Java apis en realidad utilizan Runnable, no sólo Thread's. Puede publicar Runnable utilizando el controlador, o puede utilizarlo con los ejecutores. Los runnables son buenos porque puedes implementarlos en una forma de implementación anónima.

- Thread

Un Thread es un hilo de ejecución en un programa. La máquina virtual de Java permite que una aplicación tenga varios subprocesos de ejecución ejecutándose simultáneamente.

Cada subproceso tiene una prioridad. Los hilos con mayor prioridad se ejecutan de preferencia a los hilos con menor prioridad. Cada subproceso puede o no estar marcado como un daemon. Cuando el código que se ejecuta en algún subproceso crea un nuevo objeto Thread, el nuevo subproceso tiene su prioridad inicialmente establecida igual a la prioridad del subproceso de creación y es un subproceso de daemon si y sólo si el subproceso de creación es un daemon.

### Ciclo de vida de un Thread

1. Nuevo (New): El thread ha sido creado pero no inicializado, es decir, no se ha ejecutado todavía el método start(). Se producirá un mensaje de error (IllegalThreadStateException) si se intenta ejecutar cualquier método de la clase Thread distinto de start().

2. Ejecutable (Runnable): El thread puede estar ejecutándose, siempre y cuando se le haya asignado un determinado tiempo de CPU. En la práctica puede no estar siendo ejecutado en un instante determinado en beneficio de otro thread.

3. Bloqueado (Blocked o Not Runnable): El thread podría estar ejecutándose, pero hay alguna actividad interna suya que lo impide, como por ejemplo una espera producida por una operación de escritura o lectura de datos por teclado (E/S). Si un thread está en este estado, no se le asigna tiempo de CPU.

4. Muerto (Dead): La forma habitual de que un thread muera es finalizando el método run(). También puede llamarse al método stop() de la clase Thread, aunque dicho método es considerado “peligroso” y no se debe utilizar.


### Start - Sleep - Yield - Join
- Start: 
