import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoDescarga {
	public static void main(String[] args) {
		// Completa una lista de al menos 10 URLS de descarga
	    String urlsArchivos[] = {"http://sunsite.rediris.es/mirror/ubuntu-releases/16.10/ubuntu-16.10-desktop-amd64.iso",
	    						 "http://sunsite.rediris.es/mirror/FreeBSD/ISO-IMAGES-amd64/8.2/FreeBSD-8.2-RELEASE-amd64-dvd1.iso.xz",
	    		                 "http://sunsite.rediris.es/mirror/archlinux/iso/2016.10.01/archlinux-2016.10.01-dual.iso",
	    		                 "http://sunsite.rediris.es/mirror/Lliurex/releases/14.06_64bits/releases/lliurex-escriptori-amd64_1406_20140826.iso",
	    		                 "http://sunsite.rediris.es/mirror/CentOS/7.2.1511/isos/x86_64/CentOS-7-x86_64-Everything-1511.iso",
	    		                 "http://sunsite.rediris.es/mirror/Gentoo-Releases/amd64/autobuilds/current-stage3-amd64/install-amd64-minimal-20161027.iso",
	    		                 "http://sunsite.rediris.es/mirror/DragonFlyBSD/iso-images/dfly-x86_64-4.6.1_REL.iso",
	    		                 "http://sunsite.rediris.es/mirror/OpenBSD/6.0/amd64/cd60.iso",
	    		                 "http://sunsite.rediris.es/mirror/freedos/files/distributions/1.0/fdbasecd.iso",
	    		                 "http://sunsite.rediris.es/mirror/freedos/files/distributions/1.0/fdbasews.iso"
	    		                };
	    
	    // TODO: crear el ExecuteService. El thread pool debe ser fijo (2 threads).
	    ExecutorService threadPool = Executors.newFixedThreadPool(2);
	    
	    // TODO: crear el CompletionService
	    CompletionService<String> pool = new ExecutorCompletionService<String>(threadPool);
	    
	    // TODO: iterar y añadir los callables parametrizados con urlsArchivos y un tiempo
	    // variable de 10 a 20 segundos.
	    Random random = new Random();
	    for (int i = 0; i < urlsArchivos.length; i++) {
	    	System.out.println("Descargando archivo de: " + urlsArchivos[i] +".");
	    	int downloadTime = random.nextInt(11) + 10;
	    	pool.submit(new DescargaCallable(urlsArchivos[i], downloadTime));
	    	System.out.println("");
	    }
	    
	    // TODO: imprimir los resultados a medida que los recibimos.	    
	    for (int i = 0; i < urlsArchivos.length; i++) {
	    	
	    	String resultado = null;
			try {
				resultado = pool.take().get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	// TODO: Imprimir el estado del thread pool después de recibir un archivo (toString)
	    	System.out.println(resultado);
	    	System.out.println("Estado del thread: " + threadPool.toString() + ".");
	    	
	    }
	    
	    // TODO: parar el thread pool de forma ordenada.
	    threadPool.shutdown();
	    System.out.println("Finished.");
		
	}

}
