Here’s a sample README.md for your Huffman Compression repository:

---

# Huffman Compression

Huffman Compression is a Java project implementing Huffman coding for efficient data compression and decompression. Huffman coding is a popular lossless algorithm that assigns variable-length codes to input characters, with shorter codes for more frequent characters.

## Features

- Encode text files using Huffman coding
- Decode compressed files back to original text
- Efficient data structures for tree and code generation
- Simple command-line usage and easy integration
- 100% Java

## Getting Started

### Prerequisites

- Java 8 or later

### Build & Run

1. **Clone the repository**
    ```bash
    git clone https://github.com/itaischwarz/huffmancompression.git
    cd huffmancompression
    ```

2. **Compile the code**
    ```bash
    javac *.java
    ```

3. **Run the program**
    - To compress a file:
        ```bash
        java Main compress input.txt output.huff
        ```
    - To decompress a file:
        ```bash
        java Main decompress output.huff restored.txt
        ```

## Usage

Typical usage involves specifying an input file to compress and then decompressing it back:

```bash
java Main compress example.txt example.huff
java Main decompress example.huff example_restored.txt
```

## How It Works

- The program reads the input file and calculates the frequency of each character.
- It builds a Huffman tree based on the frequencies.
- It generates Huffman codes for each character and compresses the input.
- Decompression reconstructs the original text using the Huffman tree.

## Project Structure

```
.
├── Main.java
├── HuffmanEncoder.java
├── HuffmanDecoder.java
├── Node.java
├── README.md
```

## Contributing

Feel free to submit issues or pull requests for feature requests, improvements, or bug fixes.

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---

Would you like to customize or add more details to this README? If you want this file added to your repository, let me know!
