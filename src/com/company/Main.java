package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //берём нужный нам бубн, сейчас будем шаманить ^.^
        System.out.println("Hello Work! *O*");  //начинаем *О*

        int t; //переменная выбора
        Matrix M = new Matrix(); //матрица инцедентности
        Scanner scan = new Scanner(System.in); //считывание чисел

        System.out.println("Работа с матрицей инцедентности");
        do
            {
                System.out.println("\nВыберите действие:");
                System.out.println("1.Использовать тестовую матрицу смежности"); //здесь тестовая матрица и матрица смежности уже готовые
                System.out.println("2.Задать матрицу смежности"); //здесь пользователь задаёт матрицу и сразу собирается матрица смежности
                System.out.println("3.Получить матрицу с помощью списков смежности"); //
                System.out.println("4.Посмотреть матрицу смежности"); //смотрим матрицу смежности
                System.out.println("5.Проверить связность графа");
                System.out.println("6.Добавить вершину");
                System.out.println("7.Удалить вершину");
                System.out.println("8.Добавить ребро");
                System.out.println("9.Удалить ребро");
                System.out.println("10.Поиск пути с помощью алгоритма Дейкстры");
                System.out.println("0.Выход\n");
                System.out.print("Выбор: ");
                t = scan.nextInt();

                switch (t)
                {
                    case 1: //через геттеры получаем тестовую матрицу смежности
                        {
                            M.setN(5);
                            System.out.println("\nМатрица смежности: ");
                            M.setConnmatr(M.getconnmatrix());
                            M.callM(M.getConnmatr());
                            break;
                        }

                    case 2: //решаем заполнить сами матрицу инцедентности
                        {
                            System.out.print("Введите количество вершин: ");
                            int top = scan.nextInt(); //переменная вершин
                            M.setN(top);

                            int [][] test = new int [M.getN()][M.getN()]; //временное хранение матрицы
                            M.installnull(M.getN(), M.getN(), test);

                            System.out.println("\nПостроение матрицы...");
                            for (int i = 0 ; i<top-1 ; i++)
                            {
                                for (int j = 1; j < top; j++)
                                {
                                    if ( i == j )
                                    {
                                        System.out.println("Элемент [" + (i) + "][" + (j) + "] и элемент [" + (j) + "][" + (i) + "] равняется 0");
                                        continue;
                                    }
                                    System.out.println("Введите элемент [" + (i) + "][" + (j) + "] и элемент [" + (j) + "][" + (i) + "]" );
                                    int elem = scan.nextInt();
                                    test[i][j] = elem;
                                    test[j][i] = elem;
                                }
                            }

                            System.out.println("\nПостроение закончено...");
                            System.out.println("\nМатрица смежности: ");
                            M.setConnmatr(test);
                            M.callM(M.getConnmatr());
                            break;
                        }

                    case 3: //получаем матрицу через списки смежности
                    {
                        System.out.print("Введите количество вершин: ");
                        int top = scan.nextInt();
                        int[][] test = new int[top][top];
                        M.installnull(top, top, test);

                        System.out.println("Введите список смежности для каждой вершины: ");
                        try
                        {
                            for (int i = 0; i < top; i++) {
                                System.out.printf("%d", i);
                                System.out.printf(": \n");
                                Scanner sc = new Scanner(System.in);
                                String s = "s";
                                while (!s.equals(""))
                                {
                                    s = sc.nextLine();
                                    if (!s.equals(""))
                                    {
                                        int j = Integer.parseInt(s);
                                        test[i][j] = 1;
                                        test[j][i] = 1;
                                    }
                                }
                            }
                            M.setConnmatr(test);
                            M.callM(M.getConnmatr());
                        } catch (Exception e)
                            {
                                System.out.println("Ошибка ввода!");
                            }

                        break;
                    }

                    case 4://смотрим на нашу красивую матрицу смежности, которую мы собрали/взяли/наколдовали (нужное подчеркнуть)
                        {
                            System.out.println("\nМатрица смежности: ");
                            M.callM(M.getConnmatr());
                            break;
                        }

                    //меняем бубн
                    case 5://проверяем связность графа
                        {
                            if (M.connect()==true)
                                System.out.println("Граф связный");
                            else
                                System.out.println("Граф не связный");
                            break;
                        }

                    //очень быстро меняем бубн
                    case 6: //добавляем вершину
                        {
                            //просто добавляем без запроса, какую именно и где, и куда, и когда, и зачем, и почему
                            M.add_top();
                            M.callM(M.getConnmatr());
                            break;
                        }

                    case 7: //удаляем вершину
                        {
                            System.out.println("Введите номер удалемой вершины ");
                            int v = scan.nextInt();
                            try
                            {
                                M.del_top(v);

                            } catch (Exception e)
                            {
                                System.out.println("Такой вершины нет");
                            }
                            M.callM(M.getConnmatr());
                            break;
                        }

                    case 8: //добавляем ребро
                        {
                            System.out.println("Куда добавляем ребро?");
                            System.out.print("Номер первой вершины: ");
                            int v1 = scan.nextInt();
                            System.out.print("Номер второй вершины: ");
                            int v2 = scan.nextInt();
                            try
                            {
                                M.add_rib(v1,v2);

                            }catch (Exception e)
                                {
                                    System.out.println("Ошибка!");
                                }

                            M.callM(M.getConnmatr());
                            break;
                        }

                    case 9: //удаляем ребро
                        {
                            System.out.println("Введите номер первой вершины");
                            int v1 = scan.nextInt();
                            System.out.println("Введите номер второй вершины");
                            int v2 = scan.nextInt();
                            try {
                                M.del_rib(v1, v2);
                            }
                            catch (Exception e)
                            {
                                System.out.println("Неправельнный ввод данных!");
                            }

                            M.callM(M.getConnmatr());
                            break;
                        }

                    //достаём САААААМЫЙ большой бубн из коллекции
                    case 10://алгоритм Дейкстра
                        {
                            Deykstra dey = new Deykstra();
                            System.out.println("Введите стартовую вершину ");
                            dey.setS(scan.nextInt());
                            System.out.println("Введите конечную вершину ");
                            dey.setF(scan.nextInt());
                            try {
                                dey.AlgDeykstr(M.getConnmatr());
                            }
                            catch (Exception e)
                            {
                                System.out.println("Ошибка ввода");
                            }

                            break;
                        }

                    case 0:
                        {
                            //бубны обновили, пошаманили и норм *О*
                            break;
                        }

                }

            }
        while (t>0 && t<11);
    }
}
