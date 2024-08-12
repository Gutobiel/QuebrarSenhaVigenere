import java.util.ArrayList;
import java.util.HashMap;

public class QuebradoraSenha {
	private static final String criptograma = "0053270f21331264022261252d01222f9b360367a853310b672c9a3009232e5320036722012d16332e14360721281264173224533115266106290767329a360f22611721462328152114222f07211567221a2214263253200367029a37073561112515222017251567241e640a223501251567251664132a205337032929126a4613331230076a3216640222610629076737163615a42e53370f2a311f2d002e2212200767251664132a205329072e3253230335201f64052e2701254623245337132532072d12322894a70967311c280f262d152504ae351a27076b611a2a10222f0725022661032b14670d162b082261312512332800300767001f260335351a64052233102546232453755271745d642767281d320329a6902b46232053270f21331264022261252d01222f9b360367a8532114352017250b222f0721462635012d0432ac17254626613128072e321664022261252d01222f9b36037c61162a05282f0736076a321664093528142d08262d1e210833245320033422012d122661032b1467061a2b10262f53060733280030076703162807342e532a0967321631462b280536096725123007232e532003677046715567221c2946286107a912322d1c642a2661102d0035205320032b6f53170f206f53030f2837122a460520072d1533205306032b20002b48670400300767221a221426619a640b3228072b46242e1d2c032428172546372e01351322619a6400a6221a2846232453340335221626033561166402226103b01467241e641635a0072d05266d5334073524102108232e5f6407673006210b6735162946372e062707673101a5122e22126846363416648f67281d3513222301a510222d536c0f292516270f21339232032b685d6425282f00211732241d30032a241d30036b611e310f332e006416352e1436072a20172b142232532d0b372d16290329351236072a6116371732241e251567251664053528033009203312220f26611d25156732062515672003280f242094b10334610231036732902b46292e53211534241d270f262d53270f2133123746232453120f20241dac14226d5321463634166415a42e53220724281f29032935166417322411360723200064162833533513262d023103356110360f37351c2508262d1a3712266f";
	private static final int TAMANHO_SENHA = 5;
	
	public static void main(String[] args) {
		// Declaração de variáveis
		ArrayList<HashMap<Integer, Integer>> tabelaOcorrenciasPorConjunto =
											new ArrayList<HashMap<Integer, Integer>>();
		
		// Inicialização da estrutura de dados
		for (int i = 0 ; i < TAMANHO_SENHA ; i++) { // Cada letra da senha (conjunto)
			HashMap<Integer, Integer> mapa = new HashMap<Integer, Integer>();
			for (int j = 0 ; j < 256 ; j++) {
				mapa.put(j, 0);
			}
			tabelaOcorrenciasPorConjunto.add(mapa);
		}
		
		// Montagem da estrutura de dados com ocorrências de caracteres por conjunto
		for (int i = 0 ; i < criptograma.length()/2 ; i+=2) { // Cada letra do criptograma
			int conjunto = ((i/2) % TAMANHO_SENHA);
			int letra = Integer.parseInt(criptograma.substring(i, i + 2), 16);
			
			HashMap<Integer, Integer> mapa = tabelaOcorrenciasPorConjunto.get(conjunto);
			mapa.put(letra, mapa.get(letra) + 1);
			tabelaOcorrenciasPorConjunto.set(conjunto, mapa);
		}
		
		// Descoberta da maior ocorrência da letra por conjunto
		System.out.println("Conjunto\tLetra");
		for (int i = 0 ; i < TAMANHO_SENHA ; i++) { // Cada letra da senha (conjunto)
			HashMap<Integer, Integer> mapa = tabelaOcorrenciasPorConjunto.get(i);
			int maior = 0;
			int letraMaior = 0;
			for (int j = 0 ; j < 256 ; j++) { // Cada letra do criptograma
				int quantidade = mapa.get(j);
				if (quantidade > maior) { 
					maior = quantidade;
					letraMaior = j;
				}
			}
			System.out.println(i + "\t\t" + ((char) (letraMaior ^ 32)));
		}
	}
}