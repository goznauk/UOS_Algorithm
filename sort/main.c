#include <stdio.h>
#include <assert.h>

// 함수 원형 선언
void mergeSort(int* arr, int* arrbuf, int begin, int last);
void merge(int* arr, int* arrbuf, int firstbegin, int firstLast, int secondLast);
void printArray(int* arr, int length);
void printSortedArray(int* arr, int length);
int isSorted(int *array, int num);
void functionVerify();

// main함수
int main()
{
    functionVerify();
    return 0;
}

// mergeSort
void mergeSort(int* arr, int* arrbuf, int begin, int last)
{
	int middle;
	
    // 예외처리
    if(arr == NULL || arrbuf == NULL) return;

    if(begin < last) {
        middle = (begin + ((last - begin) / 2));
        mergeSort(arr, arrbuf, begin, middle);
        mergeSort(arr, arrbuf, middle+1, last);
        merge(arr, arrbuf, begin, middle, last);
    }
}

// Combine을 담당하는 함수
void merge(int* arr, int* arrbuf, int firstbegin, int firstLast, int secondLast)
{
    int index1, index2, iterator;
    index1 = firstbegin;
    index2 = firstLast+1;
   
	printf("merge(arr, buf, %d, %d, %d) called\n", firstbegin, firstLast, secondLast);
	printf("i arr :");
	printArray(arr, 20);
	printArray(arr, 40);

    // 예외처리
    if(arr == NULL || arrbuf == NULL) return;
    
    // 버퍼에 배열 복사
    for(iterator = firstbegin; iterator <secondLast+1; iterator++)
    {
        arrbuf[iterator] = arr[iterator];
    }
    
    for(iterator = firstbegin; iterator<secondLast+1; iterator++)
    {
		printf("iterator : %d, index1 : %d, index2 : %d\n", iterator, index1, index2);
        if(index1 > firstLast){
            arr[iterator] = arrbuf[index2++];
        } else if (index2 > secondLast){
            arr[iterator] = arrbuf[index1++];
        } else if (arrbuf[index1] < arrbuf[index2]){
            arr[iterator] = arrbuf[index1++];
        } else {
            arr[iterator] = arrbuf[index2++];
        }
    }
	printf("f arr :");
	printArray(arr, 20);
	printf("f buf :");
	printArray(arrbuf, 20);
}

// 배열 출력
void printArray(int* arr, int length)
{
    int i;
    
    // 예외처리
    if(arr == NULL) return;

    printf("배열 : ");
    for(i = 0; i < length; i++)
    {
        printf("%2d ",arr[i]);
    }
    printf("\n");
}


// 정렬된 배열 출력
void printSortedArray(int* arr, int length)
{
    int i;
    
    // 예외처리
    if(arr == NULL) return;

    printf("정렬된 배열 : ");
    for(i = 0; i < length; i++)
    {
        printf("%d ",arr[i]);
    }
    printf("\n");
}

// 정렬여부 확인
// 오름차순으로 정렬되어 있으면 1을 return, 아니면 0을 return.
// 예외발생시 -1 return.
int isSorted(int *array, int num)
{
    int i;
    
    // 예외처리
    if(array == NULL) return -1;

    for(i = 0; i < num-1; i++)
    {
        if(array[i] > array[i+1])
        {
            return 0;
        }
    }
    return 1;
}

// 함수 검증
void functionVerify()
{
    // 원소 개수 관점 Test Case
    int numOfElementTest4[20] = { 20, 10, 8, 2, 9, 15, 7, 3, 1, 4, 5, 19, 16, 6, 11, 12, 14, 13, 17, 18 };

    // Test Case 크기
    int sizeOfElementTest4 = sizeof(numOfElementTest4)/sizeof(int);
    
    //Test Case 지원 Buffer
    int numOfElementTestBuffer4[20] = {0};
    
    // 소팅 진행
    mergeSort(numOfElementTest4, numOfElementTestBuffer4, 0, sizeOfElementTest4);

    // 검증
    printSortedArray(numOfElementTest4, 20);
	printf("%d", isSorted(numOfElementTest4, 20));
}
