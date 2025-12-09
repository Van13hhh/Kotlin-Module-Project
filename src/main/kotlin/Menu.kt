import java.util.Scanner

class Menu(
    private val title: String = "",
    private val items: List<MenuItem>,
    private val scanner: Scanner
) {
    fun show(): Boolean {
        while (true) {
            printMenu()

            val input = scanner.nextLine().trim()

            if (input.isEmpty()) {
                println("Пожалуйста, введите номер пункта меню")
                continue
            }

            val choice = try {
                input.toInt()
            } catch (e: NumberFormatException) {
                println("Необходимо ввести цифру!")
                continue
            }

            when {
                choice < 0 || choice >= items.size -> {
                    println("Пункт $choice не существует!")
                }
                else -> {
                    val selectedItem = items[choice]
                    if (selectedItem.isExit) {
                        return true
                    }
                    selectedItem.action?.invoke()
                    return false
                }
            }
        }
    }

    private fun printMenu() {
        if (title.isNotEmpty()) {
            println(title)
        }

        for (i in items.indices) {
            println("$i. ${items[i].name}")
        }

        print("\n Выберите пункт меню: ")
    }
}

class MenuItem(
    val name: String,
    val isExit: Boolean = false,
    val action: (() -> Unit)? = null
)