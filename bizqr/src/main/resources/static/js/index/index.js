document.querySelectorAll('.storeType a').forEach(item => {
    item.addEventListener('click', function(e) {
        e.preventDefault();
        const storeType = this.textContent; // 'All', 'Cafe', 등의 텍스트를 가져옴

        console.log(storeType);
        fetchStoresByType(storeType);
    });
});

async function fetchStoresByType(storeType) {
    try {
        const response = await fetch(`/filter?type=${storeType}`);
        const stores = await response.json();
        displayStores(stores);
    } catch (error) {
        console.error('Error fetching stores:', error);
    }
}

function displayStores(stores) {
    const storeListDiv = document.querySelector('.store-list');
    storeListDiv.innerHTML = ''; // 기존 목록을 비움
    stores.forEach(store => {
        const storeDiv = document.createElement('div');
        storeDiv.className = 'item';
        storeDiv.innerHTML = `
            <img src="/upload/storeLogo/${store.logoImage}" alt="이미지">
            <div class="info">
                <div>
                    <h5>${store.storeName}</h5>
                    <div class="btc">
                        <p>${store.company}</p>
                    </div>
                </div>
                <p>5 of 33</p>
            </div>
            <div class="bid">
                <p>조회수</p>
                <a href="#">Place a Bid</a>
            </div>
        `;
        storeListDiv.appendChild(storeDiv);
    });
}
