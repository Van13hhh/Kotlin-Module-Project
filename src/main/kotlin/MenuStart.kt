import java.util.Scanner

class MenuStart {
    private val scanner = Scanner(System.`in`)
    private val archives = mutableListOf<Archive>()

    fun start() {
        while (true) {

            val archiveItems = mutableListOf<MenuItem>()
            archiveItems.add(MenuItem("Создать архив") {
                createArchive()
            })

            archives.forEach { archive ->
                archiveItems.add(MenuItem(archive.name) {
                    openArchive(archive)
                })
            }
            archiveItems.add(MenuItem("Выход", true))
            val archiveMenu = Menu("Список архивов", archiveItems, scanner)
            val exit = archiveMenu.show()
            if (exit) {
                break
            }
        }
    }

    private fun createArchive() {
        println("\n Создание архива")
        print("Введите название архива: ")
        val name = scanner.nextLine().trim()

        if (name.isEmpty()) {
            println("Название архива не может быть пустым!")
            return
        }

        archives.add(Archive(name))
    }

    private fun openArchive(archive: Archive) {
        while (true) {

            val noteItems = mutableListOf<MenuItem>()

            noteItems.add(MenuItem("Создать заметку") {
                createNote(archive)
            })

            archive.notes.forEach { note ->
                noteItems.add(MenuItem(note.title) {
                    showNote(note)
                })
            }

            noteItems.add(MenuItem("Назад", true))

            val noteMenu = Menu("АРХИВ: ${archive.name}", noteItems, scanner)

            val Back = noteMenu.show()

            if (Back) {
                break
            }
        }
    }

    private fun createNote(archive: Archive) {

        print("Введите название заметки: ")
        val title = scanner.nextLine().trim()

        if (title.isEmpty()) {
            println("Название заметки не может быть пустым!")
            return
        }

        print("Введите содержимое заметки: ")
        val content = scanner.nextLine().trim()

        if (content.isEmpty()) {
            println("Содержимое заметки не может быть пустым!")
            return
        }

        val note = Note(title, content)
        archive.addNote(note)
    }

    private fun showNote(note: Note) {
        println("Заметка: ${note.title}")
        println("Содержимое ${note.content}")
    }


}
