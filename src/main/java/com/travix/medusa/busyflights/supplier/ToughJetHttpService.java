package com.travix.medusa.busyflights.supplier;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.SampleCreationUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ToughJetHttpService {

    public List<ToughJetResponse> searchFlights(ToughJetRequest req) {

        int n = ThreadLocalRandom.current().nextInt(0, 10);
        return SampleCreationUtil.createToughJetResponses(req, n);
    }
}
