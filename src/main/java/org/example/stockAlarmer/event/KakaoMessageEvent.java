package org.example.stockAlarmer.event;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public sealed class KakaoMessageEvent permits KakoFailEvent, KakaoSuccessEvent {
    private final String message;

    public static KakaoSuccessEvent success(String message) {
        return new KakaoSuccessEvent(message);
    }

    public static KakoFailEvent fail(String message) {
        return new KakoFailEvent(message);
    }
}
