#include<stdio.h>
#include<time.h>

int main() {
	unsigned long long target, i, f, flag;
	clock_t iTime, fTime;
	target = (1L << 61) - 1;
//	target = 134825432987324491;
	printf("target : %llu\n", target);
	iTime = clock();
	if(target%2 == 0) {
		printf("%llu = %d * %llu\n", target, 2, target/2);
		flag++;
	}
	f = target/3;
	for(i=3; i<f; i+=2) {
		if(target%i == 0) {
			printf("%llu = %llu * %llu\n", target, i, target/i);
			flag++;
		}
		f = target/(i+2);
	}
	if(flag == 0) {
		printf("is a Prime Number\n");
	} else {
		printf("is not a Prime Number\n");
	}
	fTime = clock();
	printf("Time : %f\n", ((double)(fTime - iTime)) / CLOCKS_PER_SEC);  
}
