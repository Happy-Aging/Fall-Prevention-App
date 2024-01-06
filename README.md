# HappyAging-Fall-Prevention-App

해피에이징은 낙상예방 전문 사회적기업입니다.

어르신 낙상사고 예방을 위한 인식 개선 및 교육 프로그램을 연구하고
효과적인 예방용품을 개발 및 공급함으로써
어르신이 건강하고 행복한 사회를 만드는데 기여합니다.


# App Architecture Guide
**Presentation**
- View에서는 ViewModel을 통해서만 데이터를 호출해주세요.
- ViewModel에서는 비지니스로직의 복잡도에 따라 Usecase를 호출하거나 Repository를 호출해주세요.
- ViewModel에서는 데이터를 가져온후 Success인지 Error인지를 처리하여야 합니다.
- ViewModel에서 데이터의 복잡도에 따라 Data Layer의 Model을 사용하거나 UiModel을 사용해주세요.

**Domain**
- Domain Layer에서는 다른 Layer에 대한 의존성이 있어서는 안됩니다.
- Repository의 함수 호출의 인자값들이 많은 경우에는 params클래스를 사용하세요.
- Usecase는 비지니스 복잡도에 따라 선택적으로 구현하세요.
- enum과 같은 type정보는 Domain Layer에 작성하세요.

**Data**
- Data Layer에서는 Domain의 Model을 변환하여 dto혹은 entity를 사용해주세요. toDomain함수와 toData확장함수를 만들어 사용하세요.
- API와 관련된 내용은 ApiService 클래스에 작성하세요.
