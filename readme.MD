### Tutorial Membuat Aplikasi CRUD Java FXML

#### Struktur MVC pada Aplikasi CRUD

```bash
|-CRUD
|   |--bin
|   |--src
|       |--package_utama
|           |--model
|               |--file_model_1
|           |--controller
|               |--file_controller_1
|           |--view
|               |--file_view_1
|           |--Main.java
```

#### Membuat Model CRUD
*Model ini adalah tempat untuk lebih dekat kepada masalah pertukaran data antara database dan aplikasi.
1. Buatlah file SimpleMahasiswa.java di package **model**
2. Isikan **property**, **method**, dan **contructor** pada kelas tersebut. Sesuaikan dengan kebutuhan!
3. Buatlah file Mahasiswa.java dan tambahkan atribut property yang nanti bisa digunakan pada javafxnya.
4. Buatlah MahasiswaList.java sebagai kelas untuk menyimpan dan membaca data dari xml.
5. Seluruh contoh sourcecode bisa dilihat di atas.