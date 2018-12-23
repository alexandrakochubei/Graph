package com.company;

public class Deykstra {

    int infinity = 1000;
    private int s;//начало
    private int f;//конец
    private int [] x;
    private int [] t;
    private int [] h;

    public void setS(int s)
    {
        this.s = s;
    }

    public void setF(int f)
    {
        this.f = f;
    }

    public void AlgDeykstr (int[][] matrix)
    {
        int top = matrix.length;
        x = new int[top];
        t = new int[top];
        h = new int[top];

        // Инициализируем начальные значения массивов
        int u; //cчетчик вершин
        for (u = 0; u < top; u++)
        {
            t[u] = infinity; //Сначала все кратчайшие пути из s в i равны бесконечности
            x[u] = 0;        //и нет кратчайшего пути ни для одной вершины
        }

        h[s]=0; // s - начало пути, поэтому этой вершине ничего не предшествует
        t[s]=0; // Кратчайший путь из s в s равен 0
        x[s]=1; // Для вершины s найден кратчайший путь
        int v = s;    // Делаем s текущей вершиной

        while(true)
        {
            // Перебираем все вершины, смежные v, и ищем для них кратчайший путь
            for(u = 0; u < top; u++)
            {
                if(matrix[v][u] == 0) continue; // Вершины u и v несмежные
                if(x[u] == 0 && t[u] > t[v] + matrix[v][u])
                /* Если для вершины u еще не
                   найден кратчайший путь
                   и новый путь в u короче чем
                   старый, то
                */
                {
                    t[u] = t[v]+matrix[v][u];  //запоминаем более короткую длину пути в массив t и
                    h[u] = v; //запоминаем, что v->u часть кратчайшего пути из s->u

                }
            }
            // Ищем из всех длин некратчайших путей самый короткий
            int w = infinity;  // Для поиска самого короткого пути
            v = -1;
            /* В конце поиска v - вершина, в которую будет
               найден новый кратчайший путь. Она станет
               текущей вершиной */
            for(u = 0; u < top; u++) // Перебираем все вершины.
            {
                if(x[u] == 0 && t[u] < w)
                /* Если для вершины не найден кратчайший
                   путь и если длина пути в вершину u меньше
                   уже найденной, то */
                {
                    v = u; // текущей вершиной становится u-я вершина
                    w = t[u];
                }
            }
            if(v == -1) //если v не помянялось
            {
                System.out.println("\nПути из " + (s) + " в " + (f) + " не существует");
                break;
            }
            if(v == f) // Найден кратчайший путь, выводим его
            {
                System.out.printf("%d",s);
                System.out.printf(" => ");
                System.out.printf("%d", f);
                System.out.printf(" вес ");
                System.out.printf("%d",t[f]);
                System.out.println();
                System.out.println("Путь: ");

                u = f;
                while(u!=s)
                {
                    //выводим вершины по которым надо пробраться
                    System.out.printf("%d", u);
                    System.out.printf(" => ");
                    u = h[u];
                }

                System.out.printf("%d", s);
                System.out.println();
                break;
            }
            x[v]=1;
        }



    }

}
