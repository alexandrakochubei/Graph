package com.company;

import java.util.Arrays;

public class Matrix
{
    //это тестовая матрица
    private int[][] connmatrix = {  {0, 1, 1, 0, 0},
                                    {1, 0, 0, 0, 0},
                                    {1, 0, 0, 1, 1},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 0}};

    //это матрица, которые заполняет пользователь
    private int[][] Connmatr;

    private int n; //а это количество вершин

    //Геттеры и Сетторы. Начало.
    public int[][] getconnmatrix() { return connmatrix; } public void setconnmatrix(int[][] connmatrix) { this.connmatrix = connmatrix; }
    public int[][] getConnmatr() { return Connmatr; }  public void setConnmatr(int[][] connmatr) { Connmatr = connmatr; }
    public int getN() { return n; } public void setN(int n) { this.n = n; }
    //Геттеры и Сетторы. Конец :)

    //для обнуления матрицы (вроде бы и не нужно, а вроде и мусор копится)
    void installnull (int n, int m, int IN[][])
    {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                IN[i][j] = 0;
    }

    //звоним матрице и зовём её выйти во двор, семки пощелкать и по-пацански поговорить
    void callM (int CM[][])
    {
        for (int i = 0; i < CM.length; i++)
        {
            System.out.println(Arrays.toString(CM[i]));
        }
    }


    //Сможем ли дойти мы до всех вершин? Сейчас проверим
    public boolean connect()
    {
        int[] VertexState = new int[this.n];
        boolean red = false;
        int k = 0;
        int finalCount = 0;

        for (int i = 0; i < this.n; i++)
            VertexState[i] = 1; // все черные

        VertexState[0] = 2; // красная

        do
            {
                red = true;
                for (int i = 0; i < this.n; i++)
                {
                    if (VertexState[i] == 2)
                    {
                        VertexState[i] = 3;
                        k = i;
                        break;
                    }
                }

                for (int i = 0; i < this.n; i++)
                {
                    if (Connmatr[k][i] == 1 && VertexState[i] == 1)
                    {
                        VertexState[i] = 2;
                    }
                }

                for (int i = 0; i < this.n; i++)
                {
                    if (VertexState[i] == 2)
                        red = false;
                }

            } while (red == false);

            for (int i = 0; i < this.n; i++)
                if (VertexState[i] == 1)
                    finalCount++;

            if (finalCount == 0)
                return true;
            else return false;
            // Если finalCount = 0, то граф связный
    }

    public void add_rib(int v1, int v2)
    {
        this.Connmatr[v1][v2]=1;
        this.Connmatr[v2][v1]=1;
    }

    public void del_rib(int v1,int v2)
    {
        this.Connmatr[v1][v2]=0;
        this.Connmatr[v2][v1]=0;
    }

    public void del_top(int V)
    {
        int N = n;
        N--;
        int [][] test = this.Connmatr;
        int [][] Test = new int [N][N];
        installnull(N, N, Test);

        for (int i = 0; i < V; i++)
            {
                for (int j = 0; j < V; j++)
                {
                    Test [i][j] = test[i][j];
                }
            }

        if (V != N)
            {
                int u = V;
                int y = V;
                for (int i = u + 1; i < N; i++)
                {
                    for (int j = y + 1; j < N; j++)
                    {
                        Test [u][y] = test[i][j];
                    }
                }
            }

        n--;
        setConnmatr(Test);

    }

    public void  add_top()
    {
        int N = n;
        N++;
        int [][] test = this.Connmatr;
        int [][] Test = new int [N][N];
        installnull(N, N, Test);

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                Test [i][j] = test[i][j];
            }
        }

        setConnmatr(Test);
    }

}
