package com.neptuneclient.voidui.testmod.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL33.GL_SAMPLER_BINDING;
import static org.lwjgl.opengl.GL33.glBindSampler;

/**
 * This is neccessary to prevent rendering error while working with NanoVG.
 *
 * @author CuteNyami
 */
public class GLState {

    private final int[] allCaps = new int[]{
            GL_BLEND,
            GL_COLOR_LOGIC_OP,
            GL_CULL_FACE,
            GL_DEPTH_CLAMP,
            GL_DEPTH_TEST,
            GL_DITHER,
            GL_FRAMEBUFFER_SRGB,
            GL_LINE_SMOOTH,
            GL_MULTISAMPLE,
            GL_POLYGON_OFFSET_FILL,
            GL_POLYGON_OFFSET_LINE,
            GL_POLYGON_OFFSET_POINT,
            GL_POLYGON_SMOOTH,
            GL_PROGRAM_POINT_SIZE,
            GL_RASTERIZER_DISCARD,
            GL_SAMPLE_ALPHA_TO_COVERAGE,
            GL_SAMPLE_ALPHA_TO_ONE,
            GL_SAMPLE_COVERAGE,
            GL_SAMPLE_MASK,
            GL_SCISSOR_TEST,
            GL_STENCIL_TEST
    };

    private final int[] pixelStoreParameters = new int[]{
            GL_PACK_SWAP_BYTES,
            GL_PACK_LSB_FIRST,
            GL_PACK_ROW_LENGTH,
            GL_PACK_IMAGE_HEIGHT,
            GL_PACK_SKIP_PIXELS,
            GL_PACK_SKIP_ROWS,
            GL_PACK_SKIP_IMAGES,
            GL_PACK_ALIGNMENT,
            GL_UNPACK_SWAP_BYTES,
            GL_UNPACK_LSB_FIRST,
            GL_UNPACK_ROW_LENGTH,
            GL_UNPACK_IMAGE_HEIGHT,
            GL_UNPACK_SKIP_PIXELS,
            GL_UNPACK_SKIP_ROWS,
            GL_UNPACK_SKIP_IMAGES,
            GL_UNPACK_ALIGNMENT
    };

    private int lastActiveTexture = 0;
    private int lastProgram = 0;
    private int lastTexture = 0;
    private int lastSampler = 0;
    private int lastVertexArray = 0;
    private int lastArrayBuffer = 0;

    private final Map<Integer, Boolean> capabilities = new HashMap<>();
    private final Map<Integer, Integer> pixelStores = new HashMap<>();

    private int lastBlendSrcRgb = 0;
    private int lastBlendDstRgb = 0;
    private int lastBlendSrcAlpha = 0;
    private int lastBlendDstAlpha = 0;
    private int lastBlendEquationRgb = 0;
    private int lastBlendEquationAlpha = 0;

    private final int[] lastViewport = new int[4];
    private final int[] lastScissorBox = new int[4];

    public void backupGlState() {
        lastActiveTexture = glGetInteger(GL_ACTIVE_TEXTURE);
        lastProgram = glGetInteger(GL_CURRENT_PROGRAM);
        lastTexture = glGetInteger(GL_TEXTURE_BINDING_2D);
        lastSampler = glGetInteger(GL_SAMPLER_BINDING);

        lastArrayBuffer = glGetInteger(GL_ARRAY_BUFFER_BINDING);
        lastVertexArray = glGetInteger(GL_VERTEX_ARRAY_BINDING);

        Arrays.stream(allCaps).forEach(it -> capabilities.put(it, glIsEnabled(it)));
        Arrays.stream(pixelStoreParameters).forEach(it -> pixelStores.put(it, glGetInteger(it)));

        lastBlendSrcRgb = glGetInteger(GL_BLEND_SRC_RGB);
        lastBlendDstRgb = glGetInteger(GL_BLEND_DST_RGB);
        lastBlendSrcAlpha = glGetInteger(GL_BLEND_SRC_ALPHA);
        lastBlendDstAlpha = glGetInteger(GL_BLEND_DST_ALPHA);
        lastBlendEquationRgb = glGetInteger(GL_BLEND_EQUATION_RGB);
        lastBlendEquationAlpha = glGetInteger(GL_BLEND_EQUATION_ALPHA);

        glGetIntegerv(GL_VIEWPORT, lastViewport);
        glGetIntegerv(GL_SCISSOR_BOX, lastScissorBox);
    }

    public void restoreGlState() {
        glUseProgram(lastProgram);
        glBindTexture(GL_TEXTURE_2D, lastTexture);
        glBindSampler(0, lastSampler);
        glActiveTexture(lastActiveTexture);
        glBindVertexArray(lastVertexArray);
        glBindBuffer(GL_ARRAY_BUFFER, lastArrayBuffer);
        glBlendEquationSeparate(lastBlendEquationRgb, lastBlendEquationAlpha);
        glBlendFuncSeparate(lastBlendSrcRgb, lastBlendDstRgb, lastBlendSrcAlpha, lastBlendDstAlpha);

        Arrays.stream(allCaps).forEach(it -> {
            if (capabilities.get(it)) {
                glEnable(it);
            } else {
                glDisable(it);
            }
        });

        Arrays.stream(pixelStoreParameters).forEach(it -> glPixelStorei(it, pixelStores.get(it)));

        glViewport(lastViewport[0], lastViewport[1], lastViewport[2], lastViewport[3]);
        glScissor(lastScissorBox[0], lastScissorBox[1], lastScissorBox[2], lastScissorBox[3]);
    }
}
