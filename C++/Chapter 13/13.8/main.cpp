/*Question 13.8 Write a smart pointer class. A smart pointer is a data type, usually implemented with templates, that simulates a pointer while also providing automatic garbage collection. It automatically counts the number of references to a SmartPointer<T*> object and frees the object of type T when the reference count hits zero.*/


#include <iostream>
#include <cstdlib>
using namespace std;

template <typename T>
class SmartPointer{
public:
    SmartPointer(T* ptr){
        ref = ptr;
        ref_count = new unsigned;
        *ref_count = 1;
    }
    
    SmartPointer(SmartPointer<T> &sptr){
        ref = sptr.ref;
        ref_count = sptr.ref_count;
        ++*ref_count;
    }
    
    SmartPointer<T>& operator=(SmartPointer<T> &sptr){
        if (this != &sptr) {
            if (--*ref_count == 0){
                clear();
                cout<<"operator= clear"<<endl;
            }
            
            ref = sptr.ref;
            ref_count = sptr.ref_count;
            ++*ref_count;
        }
        return *this;
    }
    
    ~SmartPointer(){
        if (--*ref_count == 0){
            clear();
            cout<<"destructor clear"<<endl;
        }
    }
    
    T getValue() { return *ref; }
    
private:
    void clear(){
        delete ref;
        delete ref_count;
        ref = NULL;
        ref_count = NULL;
    }
    
protected:
    T *ref;
    unsigned *ref_count;
};

int main(){
    int *ip1 = new int();
    *ip1 = 11111;
    int *ip2 = new int();
    *ip2 = 22222;
    SmartPointer<int> sp1(ip1), sp2(ip2);
    SmartPointer<int> spa = sp1;
    sp2 = spa; //We need to clear sp2,then give it a new address
    return 0;
}
