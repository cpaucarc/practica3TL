package Gramatica;

public class Tipos {
        
    public static boolean esGramaticaTipo3(Produccion produccion){
        // Forma: A → α || A → αB
        // donde A,B ≡ VN y α ≡ VT        
        return esVN(produccion.getIzq()) && esV(produccion.getDer());
    }
     
    public static boolean esGramaticaTipo2(Produccion produccion){
        // Forma A → α
        // donde A ≡ VN y α ≡ (VN U VT)+
        return esVN(produccion.getIzq());
    }
    
    public static boolean esGramaticaTipo1(Produccion produccion){
        // Forma: αAβ → αγβ
        // donde: γ ≡ (VN U VT)+, α, β ≡ VT, A ≡ VN
        // Propiedad de No crecimiento
        // Propiedad de sensibilidad al contexto
        return cumplePropNoDecrecimiento(produccion) && cumplePropSensibleAlContexto(produccion);
    }
    
    public static boolean esGramaticaTipo0(Produccion produccion){
        // Forma: α → β
        // donde: α ≡ (VN U VT)+ → β ≡ (VN U VT)*
        if ( produccion.getIzq().trim().length() != 0){
            if (esV(produccion.getIzq()) && esV(produccion.getDer()))
                return true;
        }
        return false;
    }
       
    public static int evaluarTipoDeProduccion(Produccion produccion){
        if (esGramaticaTipo3(produccion)){
            return 3;
        }else{
            if (esGramaticaTipo2(produccion)){
                return 2;
            }else{
                if (esGramaticaTipo1(produccion)){
                    return 1;
                }else{
                    if (esGramaticaTipo0(produccion)){
                        return 0;
                    }else{
                        return 0;
                    }
                }
            }
        }
    }
    
    public static boolean cumplePropNoDecrecimiento(Produccion produccion){
        return produccion.getIzq().length() <= produccion.getDer().length();
    }
    
    public static boolean cumplePropSensibleAlContexto(Produccion produccion){
        // 0B1 ≡ 0aA1
        String patron = "[a-zA-Z0-9]*([<]{1}[a-z]+[>]{1})*.*";
        String contexto = "^";
        String izq = produccion.getIzq();      
        for (int i = 0; i < izq.length(); i++){
            
            String elemento = "" + izq.charAt(i);            
            if (esVT(elemento)){
                contexto += "[" + elemento + "]{1}";
            }else if (esVN(elemento)){
                contexto += patron;
            }
        }
        contexto += "$";
        return produccion.getDer().matches(contexto);
    }
    
    public static boolean esVN(String cadena){
        String primeraForma = "^([A-Z]{1})$";                 // Forma: A, B, C, ..., Z
        String segundaForma = "^([<]{1}[a-z]+[>]{1})$";    // Forma: <digito>, <palabra>
        return cadena.matches(primeraForma) || cadena.matches(segundaForma);
    }
    
    public static boolean esVT(String cadena){
        String palabrasReservadas = "abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|native|new|package|private|protected|public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while|true|false";
        String patron = "^[a-z\\d]*["+palabrasReservadas+"]*.*$";
        return cadena.matches(patron) && !esVN(cadena);
    }
    
    public static boolean esV(String cadena){
        String patron = "^[a-zA-Z0-9]*([<]{1}[a-z]+[>]{1})*.*$";
        return cadena.matches(patron);
    }
        
}
