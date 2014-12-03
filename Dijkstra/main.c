#include <stdio.h>
// Number of Vertices
#define N 6
// Infinite (No Connection)
#define X 30000 

// Adjacency Matrix of Graph
int w[N][N] = {{0, 4, X, X, X, 2},
	           {1, 0, 3, 4, X, X},
			   {6, 3, 0, 7, X, X},
			   {6, X, X, 0, 2, X},
			   {X, X, X, 5, 0, X},
			   {X, X, X, 2, 3, 0}};

// Distances
int d[N][N];

// calculate distances Array from Adjacency Array
void calculateDistances() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            d[i][j] = w[i][j]; // k = 0
        }
    }
    for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
				// if the distance passing through k'th Vertice is shorter than direct edge 
                if (d[i][j] > d[i][k] + d[k][j]) {
					// change distance
                    d[i][j] = d[i][k] + d[k][j];
                }
            }
        }
    }
}

void printAdjacencies() {
	printf("Adjacency Array\n");
	for(int i=0; i<N; i++) {
		for(int j=0; j<N; j++) {
			int val = w[i][j];
			if(val>=X) {
				printf("X\t");
			} else {
				printf("%d\t", val);
			}
		}
		printf("\n");
	}
	printf("\n");
}

void printDistances() {
	printf("Distances Array\n");
	for(int i=0; i<N; i++) {
		for(int j=0; j<N; j++) {
			int val = d[i][j];
			if(val>=X) {
				printf("X\t");
			} else {
				printf("%d\t", val);
			}
		}
		printf("\n");
	}
	printf("\n");
}

int main() {
	printAdjacencies();
	calculateDistances();
	printDistances();
}

