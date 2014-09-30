
public class L3Q2 {
	static int[] heap;
	static int heapSize = 0;
	
	public static void maxHeap_Insert(int valor){
		if(heapSize >= heap.length){
			heap = resize();
		}
		heap[heapSize] = valor;
		bubble_Up(heap);
		heapSize++;
		
	}
	
	private static int[] resize() {
		
		int[] novo = new int[heap.length * 2];
		for(int i = 0; i < heap.length; i++){
			novo[i] = heap[i];
		}
		return novo;
	}

	public static void bubble_Up(int[] heapAux){
		int i = heapSize;
		while (i > 0 && heap[i] >= heap[i / 2]) {
			int aux = heap[i];
			heap[i] = heap[i/2];
			heap[i/2] = aux;
			
			i = i/2;
		}
	}
	
	public static int max_Heap_Extract(){
		int r = heap[0];
		heap[0] = heap[heapSize];
		heapSize--;
		max_heapify(0);
		return r;
	}
	
	public static void max_heapify(int i) {
		int l = 2*i;
		int r = (2*i) + 1;
		int max = i;
		
		if(l <= heapSize && heap[l] > heap[max])
			max = l;
		if(r <= heapSize && heap[r] > heap[max])
			max = r;
		if(max != i){
			int aux = heap[i];
			heap[i] = heap[max];
			heap[max] = aux;
			max_heapify(max);
		}
	}
	
	public static void build_Max_Heap(){
		
		for(int i = (heapSize/2); i < 0; i++){
			max_heapify(i);
		}
	}
	
	public static void main(String[] args) {
		
		Arquivo arq = new Arquivo("L3Q2.in", "L3Q2.out");
		heap = new int[4];
		while (!arq.isEndOfFile()) {
			String palavra = "";
			palavra = arq.readString();
			
			if(palavra.equals("ADD")){

				int leitura = arq.readInt();
				maxHeap_Insert(leitura);
				arq.println("WAIT " + heapSize);
			}
			if(palavra.equals("REM")){
				if (heapSize > 0) {
					int numero = max_Heap_Extract();
					arq.println("PROC " + numero);
				}else{
					arq.println("EMPTY");
				}
			}
			if(palavra.equals("END")){
				
				heapSize = 0;
				heap = new int[4];

				if(!arq.isEndOfFile()){
					arq.println();
				}
				if(!arq.isEndOfFile()){
					arq.println();
				}
				if(!arq.isEndOfFile()){
					arq.println();
				}
			}
			
		}
		arq.close();
	}

}
