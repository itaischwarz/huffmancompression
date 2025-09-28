import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import myfileio.MyFileIO;

/**
 * The Class HuffmanCompressionUtilities.
 */
public class HuffmanCompressionUtilities {
	/** Constant representing the number of ASCII characters. */
	private final int NUM_ASCII = 128;

	/** Constant representing the start of the printable range of ASCII characters */
    private final int ASCII_PRINT_MIN = 32;    

    /** Constant representing the end of the printable range of ASCII characters */
    private final int ASCII_PRINT_MAX = 126;    
	
	/** The queue. */
	private PriorityQueue<HuffmanTreeNode> queue;
	
	/** The root. */
	private HuffmanTreeNode root;
	
	/**  The encode map - this will map a character to the bit string that will replace it. */
	private String[] encodeMap;
	
	/** The str. This is used to print the tree structure for testing purposes */
	private String str;
	
	/** The frequency weights. */
	private int[] weights;




	/** The instance of MyFileIO - fio. */
	private MyFileIO fio;
	
	/**
	 * Instantiates a new huffman compression utilities.
	 * - creates the PriorityQueue with the appropriate comparator
	 * - creates a new weights array - this will get the weights from GenWeights class
	 * - creates the encoding map that will hold the unique Huffman Encoding for each character
	 * - initialize all other private fields and set root to null;
	 */
	public HuffmanCompressionUtilities() {
		 queue = new PriorityQueue<HuffmanTreeNode>(HuffmanTreeNode.compareWeightOrd);
		root = null;
		encodeMap = new String[NUM_ASCII];
		weights = new int[NUM_ASCII];
		fio = new MyFileIO();
		//TODO: 
		// Instantiate the Priority Queue with the appropriate static comparator...
		// Initialize root to null, and all other private variables
		// including fio.
	}
	
	/**
	 * Gets the tree root.
	 *
	 * @return the tree root
	 */
	HuffmanTreeNode getTreeRoot() {
		//TODO - write this method
		return root; // remove when written
	}
	
	/**
	 * Gets the encode map.
	 *
	 * @return the encode mapi
	 */
	String[] getEncodeMap() {
		//TODO - write this method
		return encodeMap; // remove when written
	}
	
	/**
	 * Read freq weights from the given File inf.
	 * You can assume that this file has already been error checked
	 * Use fio and a BufferedReader to read the line, split into
	 * fields, and initialize the weights array/
	 *
	 * @param inf the File object connected to the file to be read
	 * @return the int[] that represent the weights....
	 */
	int[] readFreqWeights(File inf) {

		String line = "";
		BufferedReader br = fio.openBufferedReader(inf);
		try {
			while ((line = br.readLine()) != null) {

				String[]tokens = line.split(",");
				weights[Integer.parseInt(tokens[0])] = Integer.parseInt(tokens[1]);
			}
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weights;
			
/*			fio.closeFile(br);
			for(int x = 0; x < weights.length; x++) {
				System.out.print(x);
				System.out.println(" "+ weights[x]);
			}
			return weights;
		} catch (IOException e) {
			System.err.println("Error in reading file: " + inf.getName());
			e.printStackTrace();
		}
		return weights;

		
		*/
		
	}			

	/**
	 * Initialize huffman queue from the weights array.
	 * If minimize is false, then all indexes (ASCII charset) are
	 * added to the queue, independent of their weight. If minimize is true
	 * indexes with weights of 0 will be ignored.
	 *
	 * @param minimize - when true, only add indexes with non-zero 
	 *                   weights to the queue
	 */
	void initializeHuffmanQueue(boolean minimize) {
		//TODO - write this methods
		
		
		if(!minimize) {
			for(int x = 0; x < weights.length; x++) {
				
				queue.add(new HuffmanTreeNode(x, weights[x]));
				
			}
		}
		else {
			for(int x = 0; x < weights.length; x++) {
				if(weights[x] != 0) {
					
					queue.add(new HuffmanTreeNode(x, weights[x]));
				}
				
			}
		}

		
	}
	
	/**
	 * Sets the weights array.
	 *
	 * @param weights the new weights
	 */
	void setWeights(int[] weights) {
		//TODO - write this method
			this.weights = weights;
		}
	
	
	
	/**
	 * Builds the huffman tree. Make sure to:
	 * 1) initialize root to null (cleanup any prior conversions)
	 * 2) re-initialize the encodeMap
	 * 3) initialize the queue 
	 * 4) build the tree:
	 *    while the queue is not empty:
	 *       pop the head of the queue into the left HuffmanTreeNode.
	 *       if the queue is empty - set root = left, and return;
	 *       pop the head of the queue into the right HuffmanTreeNode
	 *       create a new non-leaf HuffmanTreeNode whose children are left and right,
	 *       and whose weight is the sum of the weight of the left and right children
	 *       add the new node back to the queue.
	 * 
	 * It is assumed that the weights have been initialized prior
	 * to calling this method.
	 *
	 * @param minimize - This is just passed to the method to initialize the queue.
	 */
	void buildHuffmanTree(boolean minimize) {
		HuffmanTreeNode notleaf;
		root = null;
		encodeMap = new String[NUM_ASCII];
		initializeHuffmanQueue(minimize);		
		HuffmanTreeNode left, right;
		while(!queue.isEmpty()) {
			left = queue.poll();
			if(queue.isEmpty()) {
				root = left;
				return;
			}
			right = queue.poll();
			notleaf = new HuffmanTreeNode(left.getWeight()+ right.getWeight(), left, right);
			queue.add(notleaf);
		}
		//TODO: write this method
		
	}
	
	/**
	 * Prints the node info for debugging purposes.
	 *
	 * @param level the level of the node in the tree
	 * @param ord the ordinal value of the node
	 * @param aChar the character of the node if it is printable
	 * @param code the generated huffman code
	 */
	private void printNodeInfo(int level, int ord, char aChar, String code) {
		if ((ord <ASCII_PRINT_MIN )|| (ord>ASCII_PRINT_MAX)) {
			System.out.println("Level: "+level+ "   Ord: "+ord+"[ ] = "+code);
		} else {
			System.out.println("Level: "+level+ "   Ord: "+ord+"("+aChar+") = "+code);
		}
		
	}
	
	/**
	 * Creates the huffman codes. Starting at the root node, recursively traverse the tree to create 
	 * the code. Moving to a left child appends "0" to the code, moving to the right child appends "1".
	 * If the node is a leaf, then set the appropriate entry in the encodeMap to the accumulated 
	 * code. You should never encounter a null pointer in this process... but good to check..
	 *
	 * @param node the current node
	 * @param code - the HuffmanCode specifying the path to this node.
	 *               IMPORTANT - DO NOT MODIFY code in this method when making the recursive call
	 * @param level the level of the current node in the hierarchy. This is for debugging only...
	 */
	void createHuffmanCodes(HuffmanTreeNode node, String code, int level) {
		//TODO: write this method
		if(node != null) {
			if(node.isLeaf()) {
				encodeMap[node.getOrdValue()] = code;
				
			}
			createHuffmanCodes(node.getLeft(),code.concat("0"), level+1);
			createHuffmanCodes(node.getRight(), code.concat("1"), level+1);
	}
	}
	
	/**
	 * Prints the huffman tree for debugging and JUnit test purposes...
	 * DO NOT CHANGE!!!
	 * 
	 * Uses a preorder traversal
	 *
	 * @param node the current node in the tree hierarchy
	 * @param level the level in the tree of the node
	 */
	 void printHuffmanTree(HuffmanTreeNode node, int level) {
		if (node == null) return;
		if (level == 0) str = "";
		
		if (node.isLeaf()) {
			int ordValue = node.getOrdValue();
			if ((ordValue < ASCII_PRINT_MIN) || (ordValue > ASCII_PRINT_MAX)) {
				str += level+"l"+ordValue;  // lower case "l" means non-printing char
			} else {
				str += level +"L"+node.getCharValue();  // upper case "L" means printing char
			}
		} else {
			str += level+"N";      // Not a character
		
			if (node.getLeft() != null) {
				str += ('(');
				printHuffmanTree(node.getLeft(),level+1);
				str += ')';
			}
		    if (node.getRight() != null) {
		    	str += ('(');
		    	printHuffmanTree(node.getRight(),level+1);
		    	str += (')');
		    }
		}
	}
	
	/**
	 * prints the encodeMap to a file. This is a DEBUG helper method
	 * and not required for functionality. However, it will allow you 
	 * to see the mapping between the ordinal index of each character and
	 * the Huffman code that was used during compression.
	 * 
	 * The map file will be created in the map/ directory.
	 * 
	 * @param fname - the filename of the encode file
	 * @param optimize - is the Huffman Tree optimized or not..
	 */
	void printEncodeMapToFile(String fname, boolean optimize) {
		String outfName = "map/";
		String mapExt = ".map";
		if (optimize) 
			outfName += fname.replaceAll(".txt", mapExt);
		else 
			outfName += fname.replaceAll(".txt", "_full"+mapExt);
		
		File outf = fio.getFileHandle(outfName);
		System.out.println("Writing encode map file: "+outf.getPath());
		try {
			BufferedWriter output = fio.openBufferedWriter(outf);
			for (int i = 0; i < weights.length; i++ ) {
				if (encodeMap[i] != null)
					output.write(i+","+encodeMap[i]+"\n");
			}
			fio.closeFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This is a DEBUG method that will dump the contents of the priority queue, and then
	 * restore them. A string message is added so that repeated calls are traceable.
	 * 
	 * @param msg - debug message to print to the user
	 */
	void dumpQueue(String msg) {
		System.out.println(msg);
		HuffmanTreeNode node;
		Queue<HuffmanTreeNode> saveQ = new LinkedList<HuffmanTreeNode>();
		
		while (!queue.isEmpty()) {
			node = queue.remove();
			saveQ.add(node);
			System.out.println("   wt:"+node.getWeight()+"  ord=" +node.getOrdValue()+"  id="+node.getId());
		}
		
		while (!saveQ.isEmpty()) 
			queue.add(saveQ.remove());
	} 
	
	
	// THIS IS PART OF DECODE.... DO NOT WORK ON UNTIL INSTRUCTED TO!!
	/**
	 * Traverse tree, based upon the passing in binary String. Note that
	 * a String[] is used so that the code can manipulate the string. 
	 * 
	 * The algorithm recursively traverses the tree based on the sequence of bits 
	 * until either a leaf node is encountered and the char is returned or the string of bits
	 * has been consumed without finding a character (returns -1);
	 * 
	 *
	 * @param root the root node of the tree
	 * @param binStr - input string of 1's and 0's dictating the traversal
	 * @param index - the index into the string - used to determine if the string
	 *                has been fully traversed or pick the next node (left or right)
	 *                to traverse
	 * @return the ordinal value of the decoded character
	 */
	private int traverseTree(HuffmanTreeNode root, String binStr, int index) {
		//TODO - write this method
		if(root.isLeaf()) {
			return root.getOrdValue();
		}
		if(binStr.length() == index) {
			return -1;
		}
		if(binStr.substring(index, index + 1).equals("1")){
			return traverseTree(root.getRight(), binStr, index+ 1);
		}
		else if(binStr.substring(index, index + 1).equals("0")) {
			return traverseTree(root.getLeft(), binStr, index+ 1);
		}
		return traverseTree(root, binStr, index +1);
		 // remove when completed.
	}
	
	/**
	 * Decode string.
	 * Algorithm:
	 *  If the input string is empty, return -1
	 *  Traverse the tree with the binary string
	 *  Return the decoded character if found, -1 if not
	 *
	 * @param binStr the binary string of data read from the encoded file
	 * @return the ordinal value decoded character if found, -1 if not found
	 */
	int decodeString(String binStr) {
		//TODO - write this method
		 // remove when completed.
		if(binStr.equals("")) {
			return -1;
		}
		return traverseTree(getTreeRoot(), binStr, 0);
		
	}
		

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return str;
	}
} 
