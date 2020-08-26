<main class="page payment-page">
    <section class="clean-block payment-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Payment</h2>
            </div>
            <form>
                <div class="products">
                    <h3 class="title">Checkout</h3>
                    <div class="item"><span class="price">$${evento.costo}</span>
                        <p class="item-name">${evento.nombre}</p>
                    </div>
                    <div class="total"><span>Total</span><span class="price">$${evento.costo}</span></div>
                </div>
                <div class="card-details">
                    <h3 class="title">Credit Card Details</h3>
                    <div id="paypal-button-container"></div>
                    <script src="https://www.paypal.com/sdk/js?client-id=AWgzHntYuLTb3e1oPr8y4tijD8GoZXc_pWnXGEM6LfLdTeNJ0syFpIvKlH0u3wVBz8praOBA4P8gsmEf&currency=USD" data-sdk-integration-source="button-factory"></script>
                    <script>
                        paypal.Buttons({
                            style: {
                                shape: 'rect',
                                color: 'gold',
                                layout: 'vertical',
                                label: 'paypal',

                            },
                            createOrder: function(data, actions) {
                                return actions.order.create({
                                    purchase_units: [{
                                        amount: {
                                            value: '1'
                                        }
                                    }]
                                });
                            },
                            onApprove: function(data, actions) {
                                return actions.order.capture().then(function(details) {
                                    alert('Transaction completed by ' + details.payer.name.given_name + '!');
                                });
                            }
                        }).render('#paypal-button-container');
                    </script>
                </div>
            </form>
        </div>
    </section>
</main>