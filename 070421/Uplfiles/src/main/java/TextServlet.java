import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/start")
//@WebServlet(name = "TextServlet")
public class TextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // response.setContentType("text/html;charset=Windows-1251");
        request.setCharacterEncoding("UTF-8");
        //Response.setContentType("text/html;charset=UTF-8");
        String sourcetext = request.getParameter("text");
        StringBuilder str = new StringBuilder();
      // System.out.println(name);

        try {
            File folder = new File("d:/uploads/");
            for (File file : folder.listFiles()) {
                System.out.println(file.getName());

                //создаем объект FileReader для объекта File

                String charset = "windows-1251";
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file.getAbsolutePath()), charset));
                // FileReader fr = new FileReader(file.getAbsolutePath());
                //создаем BufferedReader с существующего FileReader для построчного считывания
                // BufferedReader reader = new BufferedReader(fr);
                // считаем сначала первую строку
                String line = reader.readLine();

            //  str.append(file.getName());
                while (line != null) {

                    if (sourcetext.equals(line))
                    {
                        str.append(file.getName()).append(" ").append(line).append(" ");
                        System.out.println(line);
                    }
                    // считываем остальные строки в цикле
                    line = reader.readLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("message", "Прекрасно");
        request.setAttribute("message", str.toString());

          //request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  request.getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
    }
}
