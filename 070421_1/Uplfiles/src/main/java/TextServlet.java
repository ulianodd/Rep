import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/start")
public class TextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String sourcetext = request.getParameter("text");
        StringBuilder str = new StringBuilder();

        try {
            File folder = new File("d:/uploads/");
            for (File file : folder.listFiles()) {
                System.out.println(file.getName());

                //создаем объект FileReader для объекта File

                String charset = "windows-1251";
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file.getAbsolutePath()), charset));

                String line = reader.readLine();

                while (line != null) {
                    if (sourcetext.equals(line)) {
                        str.append(file.getName()).append(" ").append(line).append(" ");
                    }

                    line = reader.readLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("message", str.toString());
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
